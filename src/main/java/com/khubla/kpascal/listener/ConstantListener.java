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
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser;

public class ConstantListener extends AbstractkPascalListener {
   private SimpleValue value;

   public ConstantListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterConstant(pascalParser.ConstantContext ctx) {
      String sign = null;
      if (null != ctx.sign()) {
         final SignListener signListener = new SignListener(getExecutionContext());
         signListener.enterSign(ctx.sign());
         sign = signListener.getSign();
      }
      if (null != ctx.unsignedNumber()) {
         final UnsignedNumberListener unsignedNumberListener = new UnsignedNumberListener(getExecutionContext());
         unsignedNumberListener.enterUnsignedNumber(ctx.unsignedNumber());
         value = unsignedNumberListener.getValue();
         if ((null != sign) && (sign.compareTo("-") == 0)) {
            try {
               value = SimpleValue.mult(value, new SimpleValue(-1));
            } catch (final Exception e) {
               throw new RuntimeException(e);
            }
         }
      } else if (null != ctx.constantChr()) {
         final ConstantChrListener constantChrListener = new ConstantChrListener(getExecutionContext());
         constantChrListener.enterConstantChr(ctx.constantChr());
         value = constantChrListener.getValue();
      } else if (null != ctx.string()) {
         final StringListener stringListener = new StringListener(getExecutionContext());
         stringListener.enterString(ctx.string());
         value = stringListener.getValue();
      } else if (null != ctx.identifier()) {
         final IdentifierListener identifierListener = new IdentifierListener(getExecutionContext());
         identifierListener.enterIdentifier(ctx.identifier());
         final Value v = getExecutionContext().resolveVariable(identifierListener.getIdentifier());
         if (v instanceof SimpleValue) {
            value = (SimpleValue) v;
         } else {
            throw new RuntimeException("Invalid type for '" + identifierListener.getIdentifier() + "'");
         }
      } else {
         throw new RuntimeException("not implemented");
      }
   }

   @Override
   public void exitConstant(pascalParser.ConstantContext ctx) {
   }

   public SimpleValue getValue() {
      return value;
   }

   public void setValue(SimpleValue value) {
      this.value = value;
   }
}