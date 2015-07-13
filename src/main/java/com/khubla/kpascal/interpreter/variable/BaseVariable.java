package com.khubla.kpascal.interpreter.variable;

/*
* kPascal Copyright 2012, khubla.com
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
import com.khubla.kpascal.interpreter.Variable;

public abstract class BaseVariable implements Variable {
   private final String name;
   private final VarType varType;

   BaseVariable(String name, VarType varType) {
      this.name = name;
      this.varType = varType;
   }

   @Override
   public String getName() {
      return name;
   }

   @Override
   public VarType getVarType() {
      return varType;
   }
}
