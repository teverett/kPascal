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
package com.khubla.kpascal.listener.type;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.listener.AbstractPascalListener;
import com.khubla.kpascal.listener.IdentifierListener;
import com.khubla.kpascal.listener.UnsignedNumberListener;
import com.khubla.kpascal.type.StringType;
import com.khubla.kpascal.value.IntegerValue;
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser;

public class StringTypeListener extends AbstractPascalListener {
   private StringType type = null;

   public StringTypeListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterStringtype(pascalParser.StringtypeContext ctx) {
      if (null != ctx.identifier()) {
         final IdentifierListener identifierListener = new IdentifierListener(getExecutionContext());
         identifierListener.enterIdentifier(ctx.identifier());
         final Value v = getExecutionContext().resolveVariable(identifierListener.getIdentifier());
         if (v instanceof IntegerValue) {
            final int len = ((IntegerValue) v).getValue();
            type = new StringType(len);
         } else {
            throw new InterpreterException("Expected IntegerValue");
         }
      } else if (null != ctx.unsignedNumber()) {
         final UnsignedNumberListener unsignedNumberListener = new UnsignedNumberListener(getExecutionContext());
         unsignedNumberListener.enterUnsignedNumber(ctx.unsignedNumber());
         final Value v = unsignedNumberListener.getValue();
         if (v instanceof IntegerValue) {
            final int len = ((IntegerValue) v).getValue();
            type = new StringType(len);
         } else {
            throw new InterpreterException("Expected IntegerValue");
         }
      }
   }

   public StringType getType() {
      return type;
   }

   public void setType(StringType type) {
      this.type = type;
   }
}
