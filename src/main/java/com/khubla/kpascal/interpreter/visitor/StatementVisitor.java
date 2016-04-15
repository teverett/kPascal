package com.khubla.kpascal.interpreter.visitor;

/*
* kPascal Copyright 2015, khubla.com
*
*   This program is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.antlr.PascalParser.StatementContext;
import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.interpreter.Invocable;
import com.khubla.kpascal.interpreter.Procedure;
import com.khubla.kpascal.interpreter.Statement;
import com.khubla.kpascal.interpreter.VariableInstance;
import com.khubla.kpascal.interpreter.VariableInstance.VariableDeclarationType;
import com.khubla.kpascal.rtl.RTLFunctions;
import com.khubla.kpascal.value.ArrayValue;
import com.khubla.kpascal.value.SimpleValue;
import com.khubla.kpascal.value.Value;

public class StatementVisitor extends PascalBaseVisitor<Void> {
   /**
    * the context
    */
   private final Context context;
   /**
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   /**
    * value stack
    */
   private final Stack<SimpleValue> valueStack = new Stack<SimpleValue>();

   /**
    * ctor
    */
   public StatementVisitor(Context context) {
      this.context = context;
   }

   public Context getContext() {
      return context;
   }

   /**
    * resolve an identifer name to a simpleValue
    */
   private SimpleValue resolveIdentifierNameToSimpleValue(String identifierName) throws InterpreterException {
      return (SimpleValue) context.resolveStringToValue(identifierName);
   }

   @Override
   public Void visitAssignmentStatement(PascalParser.AssignmentStatementContext ctx) {
      final Void ret = visitChildren(ctx);
      final SimpleValue rhs = valueStack.pop();
      final SimpleValue lhs = valueStack.pop();
      lhs.setValue(rhs);
      return ret;
   }

   @Override
   public Void visitBlock(PascalParser.BlockContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public Void visitCompoundStatement(PascalParser.CompoundStatementContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public Void visitForStatement(PascalParser.ForStatementContext ctx) {
      try {
         /*
          * get the loop init value
          */
         final String initValueVariableIdentifier = ctx.getChild(3).getChild(0).getText();
         final SimpleValue initValueVariableValue = resolveIdentifierNameToSimpleValue(initValueVariableIdentifier);
         /*
          * get the loop end value
          */
         final String endValueVariableIdentifier = ctx.getChild(3).getChild(2).getText();
         final SimpleValue endValueVariableValue = resolveIdentifierNameToSimpleValue(endValueVariableIdentifier);
         /*
          * get the index variable name and create an instance in the local scopr
          */
         final String indexVariableIdentifier = ctx.getChild(1).getText();
         final VariableInstance variableInstance = new VariableInstance(indexVariableIdentifier, initValueVariableValue, VariableDeclarationType.variable);
         context.getCurrentScope().addVariable(indexVariableIdentifier, variableInstance);
         /*
          * get the contained statement
          */
         final StatementContext containedStatement = (StatementContext) ctx.getChild(5);
         /*
          * output
          */
         logger.info("FOR loop on variable '" + indexVariableIdentifier + "' with start value of '" + initValueVariableValue.getValue() + "'" + "' and end value of '"
               + endValueVariableValue.getValue() + "'");
         /*
          * loop
          */
         boolean loop = true;
         while (loop == true) {
            /*
             * run statement
             */
            final Statement statement = new Statement(containedStatement, context);
            statement.run();
            /*
             * increment loop counter
             */
            final SimpleValue sv = resolveIdentifierNameToSimpleValue(indexVariableIdentifier);
            sv.setValue(SimpleValue.add(sv, new SimpleValue(1)));
            /*
             * check
             */
            if (SimpleValue.equals(sv, endValueVariableValue)) {
               loop = false;
            }
         }
         return visitChildren(ctx);
      } catch (final Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   @Override
   public Void visitProcedureStatement(PascalParser.ProcedureStatementContext ctx) {
      /*
       * visit the children to put the arguments on the stack
       */
      final Void ret = visitChildren(ctx);
      try {
         /*
          * get the name
          */
         final String procedureName = ctx.getChild(0).getText();
         /*
          * argument count
          */
         final int argCount = ctx.getChild(2).getChildCount() == 1 ? 1 : (ctx.getChild(2).getChildCount() - (1 / 2));
         /*
          * build argument list
          */
         List<Value> argumentValues = null;
         if (argCount > 0) {
            argumentValues = new ArrayList<Value>();
            for (int i = 0; i < argCount; i++) {
               final Value v = valueStack.pop();
               argumentValues.add(v);
            }
         }
         /*
          * find the procedure or RTL function
          */
         if (context.getProcedures().containsKey(procedureName)) {
            /*
             * invoke pascal procedure
             */
            final Procedure procedure = context.getProcedures().get(procedureName);
            procedure.invoke(context, argumentValues);
         } else {
            /*
             * find the function
             */
            final Invocable rtlFunction = RTLFunctions.getInstance().getRTLFunction(procedureName);
            if (null != rtlFunction) {
               /*
                * invoke RTL function
                */
               rtlFunction.invoke(context, argumentValues);
            } else {
               logger.error("Unable to find procedure '" + procedureName + "'");
            }
         }
      } catch (final Exception e) {
         e.printStackTrace();
      }
      return ret;
   }

   @Override
   public Void visitSimpleExpression(PascalParser.SimpleExpressionContext ctx) {
      final Void ret = visitChildren(ctx);
      try {
         final int numberOperations = (ctx.getChildCount() - 1) / 2;
         if (numberOperations > 0) {
            SimpleValue val = valueStack.pop();
            for (int i = 0; i < numberOperations; i++) {
               final SimpleValue thisVal = valueStack.pop();
               final String operation = ctx.getChild(1).getText().toLowerCase();
               if (operation.compareTo("+") == 0) {
                  val = SimpleValue.add(val, thisVal);
               } else if (operation.compareTo("-") == 0) {
                  val = SimpleValue.subtract(val, thisVal);
               } else if (operation.compareTo("or") == 0) {
                  val = SimpleValue.or(val, thisVal);
               }
            }
            valueStack.push(val);
         }
      } catch (final Exception e) {
         e.printStackTrace();
      }
      return ret;
   }

   @Override
   public Void visitString(PascalParser.StringContext ctx) {
      final SimpleValue value = new SimpleValue(ctx.getText());
      valueStack.push(value);
      return visitChildren(ctx);
   }

   @Override
   public Void visitTerm(PascalParser.TermContext ctx) {
      // logger.info("visitTerm '" + ctx.getText() + "'");
      final Void ret = visitChildren(ctx);
      try {
         final int numberOperations = (ctx.getChildCount() - 1) / 2;
         if (numberOperations > 0) {
            SimpleValue val = valueStack.pop();
            for (int i = 0; i < numberOperations; i++) {
               final SimpleValue thisVal = valueStack.pop();
               final String operation = ctx.getChild(1).getText().toLowerCase();
               if (operation.compareTo("*") == 0) {
                  val = SimpleValue.mult(val, thisVal);
               } else if (operation.compareTo("/") == 0) {
                  val = SimpleValue.div(val, thisVal);
               } else if (operation.compareTo("div") == 0) {
                  val = SimpleValue.div(val, thisVal);
               } else if (operation.compareTo("mod") == 0) {
                  val = SimpleValue.mod(val, thisVal);
               } else if (operation.compareTo("and") == 0) {
                  val = SimpleValue.and(val, thisVal);
               }
            }
            valueStack.push(val);
         }
      } catch (final Exception e) {
         e.printStackTrace();
      }
      return ret;
   }

   @Override
   public Void visitUnsignedInteger(PascalParser.UnsignedIntegerContext ctx) {
      final SimpleValue value = new SimpleValue(Integer.parseInt(ctx.getText()));
      valueStack.push(value);
      return visitChildren(ctx);
   }

   @Override
   public Void visitUnsignedReal(PascalParser.UnsignedRealContext ctx) {
      final SimpleValue value = new SimpleValue(Double.parseDouble(ctx.getText()));
      valueStack.push(value);
      return visitChildren(ctx);
   }

   @Override
   public Void visitVariable(PascalParser.VariableContext ctx) {
      try {
         logger.info("visitVariable '" + ctx.getText() + "'");
         /*
          * visit children first so that if there is an index ie "a[i]", that i can be put on the stack before we try to resolve a[i] to a temp
          */
         final Void ret = visitChildren(ctx);
         final String identifierName = ctx.getChild(0).getText();
         if (ctx.getChildCount() == 1) {
            final SimpleValue simpleValue = resolveIdentifierNameToSimpleValue(identifierName);
            logger.info("SimpleValue '" + identifierName + "' has value '" + simpleValue.getValue() + "'");
            valueStack.push(simpleValue);
            return ret;
         } else {
            /*
             * indexed variable
             */
            final String ch1 = ctx.getChild(1).getText();
            if (ch1.compareTo("[") == 0) {
               /*
                * get the variable we're going to index into
                */
               final ArrayValue arrayValue = (ArrayValue) context.resolveStringToValue(identifierName);
               /*
                * walk indices
                */
               final int indexTokens = ctx.getChildCount() - 3;
               final int indexCount = indexTokens == 1 ? 1 : (indexTokens - 1) / 2;
               Value currentValue = arrayValue;
               for (int i = 0; i < indexCount; i++) {
                  /*
                   * pop the index
                   */
                  final SimpleValue idx = valueStack.pop();
                  final int index = idx.asInteger();
                  /*
                   * get the indexed value
                   */
                  currentValue = arrayValue.getValue(index);
               }
               /*
                * push the finally resolve array value
                */
               final SimpleValue rr = (SimpleValue) currentValue;
               logger.info("Array value '" + ctx.getText() + "' has value '" + rr.getValue() + "'");
               valueStack.push(rr);
               return ret;
            }
            // System.out.println(ctx.getChildCount());
         }
         return ret;
      } catch (final Exception e) {
         e.printStackTrace();
         return null;
      }
   }
}
