/*
 * kPascal Copyright 2018, Tom Everett
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
package com.khubla.kpascal.listener;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.value.SimpleValue;
import com.khubla.pascal.pascalParser;

public class ExpressionListener extends AbstractkPascalListener {
   private SimpleValue value;

   public ExpressionListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterExpression(pascalParser.ExpressionContext ctx) {
      if (null != ctx.simpleExpression()) {
         final SimpleExpressionListener simpleExpressionListener = new SimpleExpressionListener(getExecutionContext());
         simpleExpressionListener.enterSimpleExpression(ctx.simpleExpression());
         value = simpleExpressionListener.getValue();
         if (null != ctx.relationaloperator()) {
            final RelationalOperatorListener relationalOperatorListener = new RelationalOperatorListener(getExecutionContext());
            relationalOperatorListener.enterRelationaloperator(ctx.relationaloperator());
            if (null != ctx.expression()) {
               final ExpressionListener expressionListener = new ExpressionListener(getExecutionContext());
               expressionListener.enterExpression(ctx.expression());
               /*
                * math
                */
               try {
                  if (relationalOperatorListener.getOperator().compareTo("*") == 0) {
                     value = SimpleValue.mult(value, expressionListener.value);
                  } else if (relationalOperatorListener.getOperator().compareTo("/") == 0) {
                     value = SimpleValue.div(value, expressionListener.value);
                  } else {
                     throw new RuntimeException("not implemented");
                  }
               } catch (final InterpreterException e) {
                  throw new RuntimeException(e);
               }
            }
         }
      }
      /*
       * get the term
       */
      // if (null != ctx.term()) {
      // final TermListener termListener = new TermListener(getExecutionContext());
      // termListener.enterTerm(ctx.term());
      // term = termListener.getTerm();
      // /*
      // * iterate operands
      // */
      // for (int i = 0; i < ((ctx.children.size() - 1) / 2); i++) {
      // /*
      // * if term is a variable, resolve it
      // */
      // term = new Term(resolveTerm(term));
      // final int idx = (i * 2) + 1;
      // final String operand = ctx.getChild(idx).getText();
      // final ExpressionListener expressionListener = new ExpressionListener(getExecutionContext());
      // expressionListener.enterExpression((ExpressionContext) ctx.getChild(idx + 1));
      // if (operand.compareTo("+") == 0) {
      // term = new Term(new Variant(term.value.getAsDouble() + resolveTerm(expressionListener.getTerm()).getAsDouble()));
      // } else if (operand.compareTo("-") == 0) {
      // term = new Term(new Variant(term.value.getAsDouble() - resolveTerm(expressionListener.getTerm()).getAsDouble()));
      // } else if (operand.compareTo("*") == 0) {
      // term = new Term(new Variant(term.value.getAsDouble() * resolveTerm(expressionListener.getTerm()).getAsDouble()));
      // } else if (operand.compareTo("/") == 0) {
      // term = new Term(new Variant(term.value.getAsDouble() / resolveTerm(expressionListener.getTerm()).getAsDouble()));
      // } else if (operand.compareTo("\\\\") == 0) {
      // throw new RuntimeException("Not Implemented");
      // } else if (operand.compareTo("#") == 0) {
      // throw new RuntimeException("Not Implemented");
      // } else if (operand.compareTo("**") == 0) {
      // throw new RuntimeException("Not Implemented");
      // } else {
      // throw new RuntimeException("Unknown operation '" + operand + "'");
      // }
      // }
      // }
   }

   @Override
   public void exitExpression(pascalParser.ExpressionContext ctx) {
   }

   public SimpleValue getValue() {
      return value;
   }

   public void setValue(SimpleValue value) {
      this.value = value;
   }
}
