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
import com.khubla.kpascal.listener.type.PointerTypeListener;
import com.khubla.kpascal.listener.type.SimpleTypeListener;
import com.khubla.kpascal.listener.type.StructuredTypeListener;
import com.khubla.kpascal.type.Type;
import com.khubla.pascal.pascalParser;

public class TypeListener extends AbstractkPascalListener {
   private Type type = null;

   public TypeListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterType(pascalParser.TypeContext ctx) {
      if (null != ctx.simpleType()) {
         final SimpleTypeListener simpleTypeListener = new SimpleTypeListener(getExecutionContext());
         simpleTypeListener.enterSimpleType(ctx.simpleType());
         type = simpleTypeListener.getType();
      } else if (null != ctx.pointerType()) {
         final PointerTypeListener pointerTypeListener = new PointerTypeListener(getExecutionContext());
         pointerTypeListener.enterPointerType(ctx.pointerType());
         type = pointerTypeListener.getType();
      } else if (null != ctx.structuredType()) {
         final StructuredTypeListener structuredTypeListener = new StructuredTypeListener(getExecutionContext());
         structuredTypeListener.enterStructuredType(ctx.structuredType());
         type = structuredTypeListener.getType();
      } else {
         throw new RuntimeException("Unknown type");
      }
   }

   @Override
   public void exitType(pascalParser.TypeContext ctx) {
   }

   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }
}
