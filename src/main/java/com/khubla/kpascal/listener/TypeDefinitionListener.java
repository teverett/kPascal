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
import com.khubla.kpascal.TypeDefinition;
import com.khubla.kpascal.listener.type.FunctionTypeListener;
import com.khubla.kpascal.listener.type.ProcedureTypeListener;
import com.khubla.pascal.pascalParser;

public class TypeDefinitionListener extends AbstractPascalListener {
   public TypeDefinitionListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterTypeDefinition(pascalParser.TypeDefinitionContext ctx) {
      if (null != ctx.identifier()) {
         final IdentifierListener identifierListener = new IdentifierListener(getExecutionContext());
         identifierListener.enterIdentifier(ctx.identifier());
         TypeDefinition typeDefinition = null;
         if (null != ctx.type()) {
            /*
             * standard type
             */
            final TypeListener typeListener = new TypeListener(getExecutionContext());
            typeListener.enterType(ctx.type());
            typeDefinition = new TypeDefinition(identifierListener.getIdentifier(), typeListener.getType());
         } else if (null != ctx.functionType()) {
            /*
             * function type
             */
            final FunctionTypeListener functionTypeListener = new FunctionTypeListener(getExecutionContext());
            functionTypeListener.enterFunctionType(ctx.functionType());
            typeDefinition = new TypeDefinition(identifierListener.getIdentifier(), functionTypeListener.getType());
         } else if (null != ctx.procedureType()) {
            /*
             * procedure type
             */
            final ProcedureTypeListener procedureTypeListener = new ProcedureTypeListener(getExecutionContext());
            procedureTypeListener.enterProcedureType(ctx.procedureType());
            typeDefinition = new TypeDefinition(identifierListener.getIdentifier(), procedureTypeListener.getType());
         }
         /*
          * declare the type
          */
         getExecutionContext().getCurrentStackframe().declareType(typeDefinition);
      }
   }
}
