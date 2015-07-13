package com.khubla.kpascal.interpreter;

import java.util.Hashtable;

import com.khubla.kpascal.interpreter.variabletype.IntegerVariableType;
import com.khubla.kpascal.interpreter.variabletype.RealVariableType;
import com.khubla.kpascal.interpreter.variabletype.StringVariableType;

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
public class VariableTypes {
   private final Hashtable<String, VariableType> variableTypes = new Hashtable<String, VariableType>();

   public VariableTypes() {
      variableTypes.put("Integer", new IntegerVariableType());
      variableTypes.put("Real", new RealVariableType());
      variableTypes.put("String", new StringVariableType());
   }

   public VariableType getVariableType(String typename) {
      return variableTypes.get(typename);
   }
}
