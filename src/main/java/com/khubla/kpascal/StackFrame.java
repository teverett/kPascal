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
package com.khubla.kpascal;

import java.util.HashMap;

import com.khubla.kpascal.value.Value;

/**
 * a stack frame, containing variables
 */
public class StackFrame {
   /**
    * variables
    */
   private HashMap<String, Value> variables = new HashMap<String, Value>();
   /**
    * constants
    */
   private final HashMap<String, Value> constants = new HashMap<String, Value>();
   /**
    * functions and procedures
    */
   private final HashMap<String, FunctionOrProcedureDefinition> functionsAndProcedures = new HashMap<String, FunctionOrProcedureDefinition>();

   public void decalareConstant(String name, Value value) {
      constants.put(name, value);
   }

   public void declareFunctionOrProcedure(FunctionOrProcedureDefinition functionOrProcedureDefinition) {
      functionsAndProcedures.put(functionOrProcedureDefinition.getName(), functionOrProcedureDefinition);
   }

   /**
    * variable name and its value (which has it's type)
    */
   public void declareVariable(String name, Value value) {
      variables.put(name, value);
   }

   public Value getConstant(String name) {
      return constants.get(name);
   }

   /**
    * get variable
    */
   public Value getVariable(String name) {
      return variables.get(name);
   }

   public HashMap<String, Value> getVariables() {
      return variables;
   }

   public void setVariables(HashMap<String, Value> variables) {
      this.variables = variables;
   }
}
