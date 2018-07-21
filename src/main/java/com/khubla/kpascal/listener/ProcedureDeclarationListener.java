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
import com.khubla.kpascal.FunctionOrProcedureDefinition;
import com.khubla.kpascal.listener.ParameterGroupListener.ParameterGroup;
import com.khubla.pascal.pascalParser;

public class ProcedureDeclarationListener extends AbstractPascalListener {
   public ProcedureDeclarationListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterProcedureDeclaration(pascalParser.ProcedureDeclarationContext ctx) {
      /*
       * id
       */
      if (null != ctx.identifier()) {
         final IdentifierListener identifierListener = new IdentifierListener(getExecutionContext());
         identifierListener.enterIdentifier(ctx.identifier());
         /*
          * params
          */
         List<ParameterGroup> parameterGroups = null;
         if (null != ctx.formalParameterList()) {
            final FormalParameterListListener formalParameterListListener = new FormalParameterListListener(getExecutionContext());
            formalParameterListListener.enterFormalParameterList(ctx.formalParameterList());
            parameterGroups = formalParameterListListener.getParameterGroups();
         }
         final FunctionOrProcedureDefinition functionOrProcedureDefinition = new FunctionOrProcedureDefinition(identifierListener.getIdentifier(), parameterGroups, ctx.block());
         getExecutionContext().getCurrentStackframe().declareFunctionOrProcedure(functionOrProcedureDefinition);
      }
   }

   @Override
   public void exitProcedureDeclaration(pascalParser.ProcedureDeclarationContext ctx) {
   }
}
