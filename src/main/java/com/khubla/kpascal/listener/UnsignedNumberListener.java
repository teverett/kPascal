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
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser;

public class UnsignedNumberListener extends AbstractPascalListener {
   private Value value;

   public UnsignedNumberListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterUnsignedNumber(pascalParser.UnsignedNumberContext ctx) {
      if (null != ctx.unsignedInteger()) {
         final UnsignedIntegerListener unsignedIntegerListener = new UnsignedIntegerListener(getExecutionContext());
         unsignedIntegerListener.enterUnsignedInteger(ctx.unsignedInteger());
         value = unsignedIntegerListener.getValue();
      } else if (null != ctx.unsignedReal()) {
         final UnsignedReaListener unsignedReaListener = new UnsignedReaListener(getExecutionContext());
         unsignedReaListener.enterUnsignedReal(ctx.unsignedReal());
         value = unsignedReaListener.getValue();
      }
   }

   public Value getValue() {
      return value;
   }

   public void setValue(Value value) {
      this.value = value;
   }
}
