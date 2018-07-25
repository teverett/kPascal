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
import com.khubla.pascal.pascalParser.BlockContext;

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
   /**
    * named types
    */
   private final HashMap<String, TypeDefinition> types = new HashMap<String, TypeDefinition>();
   /**
    * labels
    */
   private final HashMap<Integer, BlockContext> labels = new HashMap<Integer, BlockContext>();

   public void declareConstant(String name, Value value) {
      constants.put(name, value);
   }

   public void declareFunctionOrProcedure(FunctionOrProcedureDefinition functionOrProcedureDefinition) {
      functionsAndProcedures.put(functionOrProcedureDefinition.getName(), functionOrProcedureDefinition);
   }

   public void declareLabel(Integer id, BlockContext blockContext) {
      labels.put(id, blockContext);
   }

   public void declareType(TypeDefinition typeDefinition) {
      types.put(typeDefinition.getName(), typeDefinition);
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

   public FunctionOrProcedureDefinition getFunctionOrProcedureDefinition(String name) {
      return functionsAndProcedures.get(name.toLowerCase());
   }

   public TypeDefinition getType(String name) {
      return types.get(name);
   }

   /**
    * get variable
    */
   public Value getVariable(String name) {
      return variables.get(name);
   }

   /**
    * remove variable
    */
   public void removeVariable(String name) {
      variables.remove(name);
   }

   public void setVariables(HashMap<String, Value> variables) {
      this.variables = variables;
   }
}
