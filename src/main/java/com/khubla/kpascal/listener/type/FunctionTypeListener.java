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
import com.khubla.kpascal.listener.FormalParameterListListener;
import com.khubla.kpascal.type.ProcedureOrFunctionType;
import com.khubla.kpascal.type.Type;
import com.khubla.pascal.pascalParser;

public class FunctionTypeListener extends AbstractPascalListener {
   private Type type;

   public FunctionTypeListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterFunctionType(pascalParser.FunctionTypeContext ctx) {
      if (null != ctx.resultType()) {
         final ResultTypeListener resultTypeListener = new ResultTypeListener(getExecutionContext());
         resultTypeListener.enterResultType(ctx.resultType());
         if (null != ctx.formalParameterList()) {
            final FormalParameterListListener formalParameterListListener = new FormalParameterListListener(getExecutionContext());
            formalParameterListListener.enterFormalParameterList(ctx.formalParameterList());
            type = new ProcedureOrFunctionType(formalParameterListListener.getParameters(), resultTypeListener.getType());
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
