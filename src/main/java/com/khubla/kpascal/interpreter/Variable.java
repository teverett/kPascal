package com.khubla.kpascal.interpreter;

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
public class Variable {
   public enum VariableDeclarationType {
      constant, variable;
   }

   public enum VariableType {
      string, integer, real, struct;
   }

   private final String name;
   private final VariableDeclarationType variableDeclarationType;
   private final VariableType variableType;
   private final String value;
   private final int ordinality;

   public int getOrdinality() {
      return ordinality;
   }

   public Variable(String name, VariableType variableType, VariableDeclarationType variableDeclarationType, int ordinality, String value) {
      this.name = name;
      this.variableType = variableType;
      this.variableDeclarationType = variableDeclarationType;
      this.value = value;
      this.ordinality = ordinality;
   }

   public String getName() {
      return name;
   }

   public String getValue() {
      return value;
   }

   public VariableDeclarationType getVariableDeclarationType() {
      return variableDeclarationType;
   }

   public VariableType getVariableType() {
      return variableType;
   }
}
