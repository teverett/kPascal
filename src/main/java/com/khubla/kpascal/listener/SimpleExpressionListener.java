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

public class SimpleExpressionListener extends AbstractPascalListener {
   private Value value;

   public SimpleExpressionListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterSimpleExpression(pascalParser.SimpleExpressionContext ctx) {
      if (null != ctx.term()) {
         final TermListener termListener = new TermListener(getExecutionContext());
         termListener.enterTerm(ctx.term());
         value = termListener.getValue();
         if (null != ctx.additiveoperator()) {
            final AdditiveOperatorListener additiveOperatorListener = new AdditiveOperatorListener(getExecutionContext());
            additiveOperatorListener.enterAdditiveoperator(ctx.additiveoperator());
            if (null != ctx.simpleExpression()) {
               final SimpleExpressionListener simpleExpressionListener = new SimpleExpressionListener(getExecutionContext());
               simpleExpressionListener.enterSimpleExpression(ctx.simpleExpression());
               /*
                * math
                */
               try {
                  if (additiveOperatorListener.getOperator().compareTo("+") == 0) {
                     value = value.add(simpleExpressionListener.value);
                  } else if (additiveOperatorListener.getOperator().compareTo("-") == 0) {
                     value = value.subtract(simpleExpressionListener.value);
                  } else if (additiveOperatorListener.getOperator().compareTo("or") == 0) {
                     value = value.or(simpleExpressionListener.value);
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
   public void exitSimpleExpression(pascalParser.SimpleExpressionContext ctx) {
   }

   public Value getValue() {
      return value;
   }

   public void setValue(Value value) {
      this.value = value;
   }
}
