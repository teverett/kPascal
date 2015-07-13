package com.khubla.kpascal.interpreter;

import java.util.Hashtable;
import java.util.Stack;

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
public class Context {
   /**
    * variables
    */
   private final Hashtable<String, Variable> variables = new Hashtable<String, Variable>();
   /**
    * stack
    */
   final Stack<Variable> stack = new Stack<Variable>();
   /**
    * types
    */
   final VariableTypes variableTypes = new VariableTypes();

   public Stack<Variable> getStack() {
      return stack;
   }

   public Hashtable<String, Variable> getVariables() {
      return variables;
   }

   public VariableTypes getVariableTypes() {
      return variableTypes;
   }
}