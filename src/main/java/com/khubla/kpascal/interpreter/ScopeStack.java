package com.khubla.kpascal.interpreter;

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
public class ScopeStack {
   /**
    * stack of execution contexts
    */
   private final Stack<Scope> scopeStack = new Stack<Scope>();

   /**
    * ctor
    */
   public ScopeStack() {
   }

   public Scope get(int i) {
      return scopeStack.get(i);
   }

   /**
    * the current scope is the scope on the top of the stack
    */
   public Scope getCurrentScope() {
      return scopeStack.get(0);
   }

   public void popScope() {
      scopeStack.pop();
   }

   public Scope pushScope() {
      final Scope scope = new Scope();
      scopeStack.push(scope);
      return scope;
   }

   public int size() {
      return scopeStack.size();
   }
}
