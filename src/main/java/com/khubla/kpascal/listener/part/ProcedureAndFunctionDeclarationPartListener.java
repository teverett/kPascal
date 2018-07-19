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
import com.khubla.kpascal.listener.AbstractkPascalListener;
import com.khubla.kpascal.listener.ProcedureOrFunctionDeclarationListener;
import com.khubla.pascal.pascalParser;

public class ProcedureAndFunctionDeclarationPartListener extends AbstractkPascalListener {
   public ProcedureAndFunctionDeclarationPartListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterProcedureAndFunctionDeclarationPart(pascalParser.ProcedureAndFunctionDeclarationPartContext ctx) {
      if (null != ctx.procedureOrFunctionDeclaration()) {
         final ProcedureOrFunctionDeclarationListener procedureOrFunctionDeclarationListener = new ProcedureOrFunctionDeclarationListener(getExecutionContext());
         procedureOrFunctionDeclarationListener.enterProcedureOrFunctionDeclaration(ctx.procedureOrFunctionDeclaration());
      }
   }

   @Override
   public void exitProcedureAndFunctionDeclarationPart(pascalParser.ProcedureAndFunctionDeclarationPartContext ctx) {
   }
}
