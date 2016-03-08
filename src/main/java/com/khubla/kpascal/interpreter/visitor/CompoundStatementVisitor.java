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
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.interpreter.Invocable;
import com.khubla.kpascal.interpreter.Procedure;
import com.khubla.kpascal.interpreter.VariableInstance;
import com.khubla.kpascal.rtl.RTLFunctions;
import com.khubla.kpascal.value.SimpleValue;
import com.khubla.kpascal.value.Value;

public class CompoundStatementVisitor extends PascalBaseVisitor<Void> {
   private final Context context;
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   private final Stack<SimpleValue> valueStack = new Stack<SimpleValue>();

   public CompoundStatementVisitor(Context context) {
      this.context = context;
   }

   /**
    * get a parameter list. return null if no arguments
    */
   private List<String> getArguments(PascalParser.ParameterListContext ctx) {
      if ((null != ctx) && (ctx.getChildCount() > 0)) {
         final List<String> ret = new ArrayList<String>();
         for (int i = 0; i < ctx.getChildCount(); i += 2) {
            final String parameter = ctx.getChild(i).getText();
            ret.add(parameter);
         }
         return ret;
      } else {
         return null;
      }
   }

   public Context getContext() {
      return context;
   }

   @Override
   public Void visitAssignmentStatement(PascalParser.AssignmentStatementContext ctx) {
      final Void ret = visitChildren(ctx);
      final String lvalName = ctx.getChild(0).getText();
      final VariableInstance variableInstance = context.resolveVariableInstance(lvalName);
      if (null != variableInstance) {
         final Value rVal = valueStack.pop();
         variableInstance.setValue(rVal);
      } else {
         throw new RuntimeException("Unable to resolve variable '" + lvalName + "'");
      }
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
      ctx.getChild(1).getText();
      ctx.getChild(3).getChild(0).getText();
      ctx.getChild(3).getChild(2).getText();
      return visitChildren(ctx);
   }

   @Override
   public Void visitProcedureStatement(PascalParser.ProcedureStatementContext ctx) {
      try {
         /*
          * get the name
          */
         final String procedureName = ctx.getChild(0).getText();
         /*
          * get the arguments, as strings
          */
         final PascalParser.ParameterListContext ctx2 = (PascalParser.ParameterListContext) ctx.getChild(2);
         final List<String> argumentNames = getArguments(ctx2);
         /*
          * convert the strings to values
          */
         List<Value> argumentValues = null;
         if (null != argumentNames) {
            argumentValues = new ArrayList<Value>();
            for (final String argument : argumentNames) {
               final Value v = context.resolveStringToValue(argument);
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
      return visitChildren(ctx);
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
   public Void visitTerm(PascalParser.TermContext ctx) {
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
         final String v = ctx.getChild(0).getText();
         final SimpleValue simpleValue = (SimpleValue) context.resolveStringToValue(v);
         valueStack.push(simpleValue);
         return visitChildren(ctx);
      } catch (final Exception e) {
         e.printStackTrace();
      }
      return visitChildren(ctx);
   }
}
