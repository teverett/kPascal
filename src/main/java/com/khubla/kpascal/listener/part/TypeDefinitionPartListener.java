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
package com.khubla.kpascal.listener.part;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.kpascal.listener.AbstractPascalListener;
import com.khubla.kpascal.listener.TypeDefinitionListener;
import com.khubla.pascal.pascalParser;
import com.khubla.pascal.pascalParser.TypeDefinitionContext;

public class TypeDefinitionPartListener extends AbstractPascalListener {
   public TypeDefinitionPartListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterTypeDefinitionPart(pascalParser.TypeDefinitionPartContext ctx) {
      if (null != ctx.typeDefinition()) {
         for (final TypeDefinitionContext typeDefinitionContext : ctx.typeDefinition()) {
            final TypeDefinitionListener typeDefinitionListener = new TypeDefinitionListener(getExecutionContext());
            typeDefinitionListener.enterTypeDefinition(typeDefinitionContext);
         }
      }
   }

   @Override
   public void exitTypeDefinitionPart(pascalParser.TypeDefinitionPartContext ctx) {
   }
}
