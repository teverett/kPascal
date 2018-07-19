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

import java.util.List;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.pascal.pascalParser;

public class ParameterGroupListener extends AbstractkPascalListener {
   public class ParameterGroup {
      private final String typename;
      private final List<String> identifiers;
      /*
       * none, VAR, FUNCTION, PROCEDURE
       */
      private String type;

      public ParameterGroup(String typename, List<String> identifiers) {
         this.typename = typename;
         this.identifiers = identifiers;
      }

      public List<String> getIdentifiers() {
         return identifiers;
      }

      public String getType() {
         return type;
      }

      public String getTypename() {
         return typename;
      }

      public void setType(String type) {
         this.type = type;
      }
   }

   private ParameterGroup parameterGroup;

   public ParameterGroupListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterParameterGroup(pascalParser.ParameterGroupContext ctx) {
      if (null != ctx.identifierList()) {
         final IdentifierListListener identifierListListener = new IdentifierListListener(getExecutionContext());
         identifierListListener.enterIdentifierList(ctx.identifierList());
         if (null != ctx.typeIdentifier()) {
            final TypeIdentifierListener typeIdentifierListener = new TypeIdentifierListener(getExecutionContext());
            typeIdentifierListener.enterTypeIdentifier(ctx.typeIdentifier());
            parameterGroup = new ParameterGroup(typeIdentifierListener.getTypeName(), identifierListListener.getIdentifiers());
         } else {
            parameterGroup = new ParameterGroup(null, identifierListListener.getIdentifiers());
         }
      }
   }

   @Override
   public void exitParameterGroup(pascalParser.ParameterGroupContext ctx) {
   }

   public ParameterGroup getParameterGroup() {
      return parameterGroup;
   }

   public void setParameterGroup(ParameterGroup parameterGroup) {
      this.parameterGroup = parameterGroup;
   }
}
