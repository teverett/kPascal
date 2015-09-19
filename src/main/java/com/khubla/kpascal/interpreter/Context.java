package com.khubla.kpascal.interpreter;

import java.util.Hashtable;
import java.util.Stack;

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
public class Context {
   /**
    * all procedures by name
    */
   private final Hashtable<String, Procedure> procedures = new Hashtable<String, Procedure>();
   /**
    * stack of execution contexts
    */
   private final Stack<Scope> scopeStack = new Stack<Scope>();
   /**
    * constants
    */
   private final Hashtable<String, VariableInstance> constants = new Hashtable<String, VariableInstance>();

   public Hashtable<String, VariableInstance> getConstants() {
      return constants;
   }

   /**
    * the current scope is the scope on the top of the stack
    */
   public Scope getCurrentScope() {
      return getScopeStack().get(0);
   }

   public Hashtable<String, Procedure> getProcedures() {
      return procedures;
   }

   public Stack<Scope> getScopeStack() {
      return scopeStack;
   }
}
