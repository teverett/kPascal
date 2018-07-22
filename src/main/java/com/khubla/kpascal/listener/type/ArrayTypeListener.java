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
import com.khubla.kpascal.listener.TypeListListener;
import com.khubla.kpascal.type.ArrayType;
import com.khubla.kpascal.type.Type;
import com.khubla.pascal.pascalParser;

public class ArrayTypeListener extends AbstractPascalListener {
   private Type type = null;

   public ArrayTypeListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterArrayType(pascalParser.ArrayTypeContext ctx) {
      if (null != ctx.typeList()) {
         final TypeListListener typeListListener = new TypeListListener(getExecutionContext());
         typeListListener.enterTypeList(ctx.typeList());
         if (null != ctx.componentType()) {
            final ComponentTypeListener componentTypeListener = new ComponentTypeListener(getExecutionContext());
            componentTypeListener.enterComponentType(ctx.componentType());
            /*
             * type
             */
            type = new ArrayType(componentTypeListener.getType(), typeListListener.getTypelist());
         }
      }
   }

   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }
}
