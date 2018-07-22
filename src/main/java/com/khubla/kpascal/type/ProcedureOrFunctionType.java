/*
* kPascal Copyright 2015, khubla.com
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
package com.khubla.kpascal.type;

import java.util.List;

import com.khubla.kpascal.listener.ParameterGroupListener.Parameter;
import com.khubla.kpascal.value.Value;

public class ProcedureOrFunctionType implements Type {
   private final List<Parameter> parameters;
   private final Type resultType;

   public ProcedureOrFunctionType(List<Parameter> parameters) {
      this.parameters = parameters;
      resultType = null;
   }

   public ProcedureOrFunctionType(List<Parameter> parameters, Type resultType) {
      this.parameters = parameters;
      this.resultType = resultType;
   }

   @Override
   public boolean builtIn() {
      return false;
   }

   @Override
   public Value createValue() {
      // TODO Auto-generated method stub
      return null;
   }

   public List<Parameter> getParameters() {
      return parameters;
   }

   public Type getResultType() {
      return resultType;
   }
}
