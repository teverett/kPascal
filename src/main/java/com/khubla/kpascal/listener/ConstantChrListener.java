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
import com.khubla.kpascal.value.IntegerValue;
import com.khubla.pascal.pascalParser;

public class ConstantChrListener extends AbstractkPascalListener {
   private IntegerValue value;

   public ConstantChrListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterConstantChr(pascalParser.ConstantChrContext ctx) {
      if (null != ctx.unsignedInteger()) {
         final UnsignedIntegerListener unsignedIntegerListener = new UnsignedIntegerListener(getExecutionContext());
         unsignedIntegerListener.enterUnsignedInteger(ctx.unsignedInteger());
         // this.value = new SimpleValue(new String((char) unsignedIntegerListener.getValue().asCharacter().intValue()));
      }
   }

   @Override
   public void exitConstantChr(pascalParser.ConstantChrContext ctx) {
   }

   public IntegerValue getValue() {
      return value;
   }

   public void setValue(IntegerValue value) {
      this.value = value;
   }
}
