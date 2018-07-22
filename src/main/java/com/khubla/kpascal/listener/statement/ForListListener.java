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
package com.khubla.kpascal.listener.statement;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.kpascal.listener.AbstractPascalListener;
import com.khubla.kpascal.value.IntegerValue;
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser;

public class ForListListener extends AbstractPascalListener {
   private IntegerValue initialValue;
   private IntegerValue finalValue;

   public ForListListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterForList(pascalParser.ForListContext ctx) {
      if (null != ctx.initialValue()) {
         final InitialValueListener initialValueListener = new InitialValueListener(getExecutionContext());
         initialValueListener.enterInitialValue(ctx.initialValue());
         final Value v = initialValueListener.getValue();
         if (v instanceof IntegerValue) {
            initialValue = (IntegerValue) v;
         } else {
            throw new RuntimeException("must be IntegerValue");
         }
      }
      if (null != ctx.finalValue()) {
         final FinalValueListener finalValueListener = new FinalValueListener(getExecutionContext());
         finalValueListener.enterFinalValue(ctx.finalValue());
         final Value v = finalValueListener.getValue();
         if (v instanceof IntegerValue) {
            finalValue = (IntegerValue) v;
         } else {
            throw new RuntimeException("must be IntegerValue");
         }
      }
   }

   public IntegerValue getFinalValue() {
      return finalValue;
   }

   public IntegerValue getInitialValue() {
      return initialValue;
   }

   public void setFinalValue(IntegerValue finalValue) {
      this.finalValue = finalValue;
   }

   public void setInitialValue(IntegerValue initialValue) {
      this.initialValue = initialValue;
   }
}
