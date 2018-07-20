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
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser;

public class TermListener extends AbstractkPascalListener {
   private Value value;

   public TermListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterTerm(pascalParser.TermContext ctx) {
      if (null != ctx.signedFactor()) {
         final SignedFactorListener signedFactorListener = new SignedFactorListener(getExecutionContext());
         signedFactorListener.enterSignedFactor(ctx.signedFactor());
         value = signedFactorListener.getValue();
         if (null != ctx.multiplicativeoperator()) {
            final MultiplicativeOperatorListener multiplicativeOperatorListener = new MultiplicativeOperatorListener(getExecutionContext());
            multiplicativeOperatorListener.enterMultiplicativeoperator(ctx.multiplicativeoperator());
            if (null != ctx.term()) {
               final TermListener termListener = new TermListener(getExecutionContext());
               termListener.enterTerm(ctx.term());
               /*
                * math
                */
               try {
                  if (multiplicativeOperatorListener.getOperator().compareTo("*") == 0) {
                     value = value.mult(termListener.value);
                  } else if (multiplicativeOperatorListener.getOperator().compareTo("/") == 0) {
                     value = value.div(termListener.value);
                  } else {
                     throw new RuntimeException("not implemented");
                  }
               } catch (final InterpreterException e) {
                  throw new RuntimeException(e);
               }
            }
         }
      }
   }

   @Override
   public void exitTerm(pascalParser.TermContext ctx) {
   }

   public Value getValue() {
      return value;
   }

   public void setValue(Value value) {
      this.value = value;
   }
}
