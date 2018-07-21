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
import com.khubla.kpascal.exception.NotImplementedException;
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser;

public class ExpressionListener extends AbstractPascalListener {
   private Value value;

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
                     value = value.mult(expressionListener.getValue());
                  } else if (relationalOperatorListener.getOperator().compareTo("/") == 0) {
                     value = value.div(expressionListener.getValue());
                  } else if (relationalOperatorListener.getOperator().compareTo(">") == 0) {
                     value = value.gt(expressionListener.getValue());
                  } else if (relationalOperatorListener.getOperator().compareTo("<") == 0) {
                     value = value.lt(expressionListener.getValue());
                  } else if (relationalOperatorListener.getOperator().compareTo("<>") == 0) {
                     value = value.neq(expressionListener.getValue());
                  } else if (relationalOperatorListener.getOperator().compareTo("=") == 0) {
                     value = value.eq(expressionListener.getValue());
                  } else if (relationalOperatorListener.getOperator().compareTo("<=") == 0) {
                     value = value.lte(expressionListener.getValue());
                  } else if (relationalOperatorListener.getOperator().compareTo(">=") == 0) {
                     value = value.gte(expressionListener.getValue());
                  } else if (relationalOperatorListener.getOperator().compareTo("in") == 0) {
                     throw new NotImplementedException();
                  } else {
                     throw new NotImplementedException();
                  }
               } catch (final InterpreterException e) {
                  throw new RuntimeException(e);
               }
            }
         }
      }
   }

   @Override
   public void exitExpression(pascalParser.ExpressionContext ctx) {
   }

   public Value getValue() {
      return value;
   }

   public void setValue(Value value) {
      this.value = value;
   }
}
