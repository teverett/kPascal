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
import com.khubla.kpascal.runtime.function.RuntimeFunction;
import com.khubla.pascal.pascalParser;

public class ProcedureStatementListener extends AbstractkPascalListener {
   public ProcedureStatementListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterProcedureStatement(pascalParser.ProcedureStatementContext ctx) {
      if (null != ctx.identifier()) {
         final IdentifierListener identifierListener = new IdentifierListener(getExecutionContext());
         identifierListener.enterIdentifier(ctx.identifier());
         if (null != ctx.parameterList()) {
            final ParameterListListener parameterListListener = new ParameterListListener(getExecutionContext());
            parameterListListener.enterParameterList(ctx.parameterList());
            /*
             * invoke
             */
            final RuntimeFunction runtimeFunction = getRuntimeFunctionFactory().getRuntimeFunction(identifierListener.getIdentifier());
            if (null != runtimeFunction) {
               runtimeFunction.execute(parameterListListener.getValues());
            } else {
               throw new RuntimeException("Unknown procedure '" + identifierListener.getIdentifier() + "'");
            }
         }
      }
   }

   @Override
   public void exitProcedureStatement(pascalParser.ProcedureStatementContext ctx) {
   }
}
