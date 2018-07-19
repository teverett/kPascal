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
import com.khubla.kpascal.listener.AbstractkPascalListener;
import com.khubla.kpascal.listener.IdentifierListener;
import com.khubla.kpascal.type.SimpleType;
import com.khubla.kpascal.type.Type;
import com.khubla.pascal.pascalParser;

public class TypeIndentifierListener extends AbstractkPascalListener {
   private Type type = null;

   public TypeIndentifierListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterTypeIdentifier(pascalParser.TypeIdentifierContext ctx) {
      if (null != ctx.identifier()) {
         final IdentifierListener identifierListener = new IdentifierListener(getExecutionContext());
         identifierListener.enterIdentifier(ctx.identifier());
         type = getExecutionContext().resolveType(identifierListener.getIdentifier());
      } else {
         final String typename = ctx.getText();
         if (typename.toLowerCase().compareTo("char") == 0) {
            type = new SimpleType(SimpleType.Type.character);
         } else if (typename.toLowerCase().compareTo("bool") == 0) {
            type = new SimpleType(SimpleType.Type.bool);
         } else if (typename.toLowerCase().compareTo("integer") == 0) {
            type = new SimpleType(SimpleType.Type.integer);
         } else if (typename.toLowerCase().compareTo("real") == 0) {
            type = new SimpleType(SimpleType.Type.real);
         } else if (typename.toLowerCase().compareTo("string") == 0) {
            type = new SimpleType(SimpleType.Type.string);
         }
      }
   }

   @Override
   public void exitTypeIdentifier(pascalParser.TypeIdentifierContext ctx) {
   }

   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }
}
