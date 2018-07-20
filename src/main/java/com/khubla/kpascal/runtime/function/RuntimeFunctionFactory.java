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
package com.khubla.kpascal.runtime.function;

import java.util.Hashtable;

import com.khubla.kpascal.ExecutionContext;

public class RuntimeFunctionFactory {
   private final ExecutionContext executionContext;
   private final Hashtable<String, RuntimeFunction> functions = new Hashtable<String, RuntimeFunction>();

   public RuntimeFunctionFactory(ExecutionContext executionContext) {
      this.executionContext = executionContext;
      addFunction("writeln", new WritelnFunction(executionContext));
      addFunction("write", new WriteFunction(executionContext));
   }

   private void addFunction(String name, RuntimeFunction runtimeFunction) {
      functions.put(name.toLowerCase(), runtimeFunction);
   }

   public ExecutionContext getExecutionContext() {
      return executionContext;
   }

   public RuntimeFunction getRuntimeFunction(String name) {
      final RuntimeFunction runtimeFunction = functions.get(name.toLowerCase());
      if (null != runtimeFunction) {
         return runtimeFunction;
      } else {
         throw new RuntimeException("Unknown function '" + name + "'");
      }
   }
}
