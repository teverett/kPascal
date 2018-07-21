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
import com.khubla.kpascal.listener.AbstractPascalListener;
import com.khubla.kpascal.listener.ConstantListener;
import com.khubla.kpascal.type.SubrangeType;
import com.khubla.kpascal.type.Type;
import com.khubla.kpascal.value.IntegerValue;
import com.khubla.pascal.pascalParser;

public class SubrangeTypeListener extends AbstractPascalListener {
   private Type type = null;

   public SubrangeTypeListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterSubrangeType(pascalParser.SubrangeTypeContext ctx) {
      if (null != ctx.constant(0)) {
         final ConstantListener constantListener = new ConstantListener(getExecutionContext());
         constantListener.enterConstant(ctx.constant(0));
         if (null != ctx.constant(1)) {
            final ConstantListener constantListener2 = new ConstantListener(getExecutionContext());
            constantListener2.enterConstant(ctx.constant(1));
            /*
             * type
             */
            if ((constantListener.getValue() instanceof IntegerValue) && (constantListener2.getValue() instanceof IntegerValue)) {
               type = new SubrangeType((IntegerValue) constantListener.getValue(), (IntegerValue) constantListener2.getValue());
            } else {
               throw new RuntimeException("Invalid subrange type");
            }
         }
      }
   }

   @Override
   public void exitSubrangeType(pascalParser.SubrangeTypeContext ctx) {
   }

   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }
}
