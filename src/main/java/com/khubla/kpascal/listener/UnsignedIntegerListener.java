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
import com.khubla.kpascal.value.SimpleValue;
import com.khubla.pascal.pascalParser;

public class UnsignedIntegerListener extends AbstractkPascalListener {
   private SimpleValue value;

   public UnsignedIntegerListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterUnsignedInteger(pascalParser.UnsignedIntegerContext ctx) {
      value = new SimpleValue(Integer.parseInt(ctx.getText()));
   }

   @Override
   public void exitUnsignedInteger(pascalParser.UnsignedIntegerContext ctx) {
   }

   public SimpleValue getValue() {
      return value;
   }

   public void setValue(SimpleValue value) {
      this.value = value;
   }
}
