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
import com.khubla.kpascal.type.Type;
import com.khubla.pascal.pascalParser;

public class SimpleTypeListener extends AbstractPascalListener {
   private Type type = null;

   public SimpleTypeListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterSimpleType(pascalParser.SimpleTypeContext ctx) {
      if (null != ctx.scalarType()) {
         /*
          * scalar
          */
         final ScalarTypeListener scalarTypeListener = new ScalarTypeListener(getExecutionContext());
         scalarTypeListener.enterScalarType(ctx.scalarType());
         type = scalarTypeListener.getType();
      } else if (null != ctx.stringtype()) {
         /*
          * string
          */
         final StringTypeListener stringTypeListener = new StringTypeListener(getExecutionContext());
         stringTypeListener.enterStringtype(ctx.stringtype());
         type = stringTypeListener.getType();
      } else if (null != ctx.subrangeType()) {
         /*
          * subrange
          */
         final SubrangeTypeListener subrangeTypeListener = new SubrangeTypeListener(getExecutionContext());
         subrangeTypeListener.enterSubrangeType(ctx.subrangeType());
         type = subrangeTypeListener.getType();
      } else if (null != ctx.typeIdentifier()) {
         /*
          * type identifier
          */
         final TypeIdentifierListener typeIndentifierListener = new TypeIdentifierListener(getExecutionContext());
         typeIndentifierListener.enterTypeIdentifier(ctx.typeIdentifier());
         type = typeIndentifierListener.getType();
      } else {
         throw new InterpreterException("Unknown type");
      }
   }

   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }
}
