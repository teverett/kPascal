package com.khubla.kpascal.interpreter;

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
import java.util.Hashtable;
import java.util.Stack;

import com.khubla.kpascal.type.BooleanType;
import com.khubla.kpascal.type.CharType;
import com.khubla.kpascal.type.IntegerType;
import com.khubla.kpascal.type.RealType;
import com.khubla.kpascal.type.StringType;
import com.khubla.kpascal.type.Types;

public class Scope {
   /**
    * variables
    */
   private final Hashtable<String, VariableInstance> variables = new Hashtable<String, VariableInstance>();
   /**
    * types
    */
   private final Types types;
   /**
    * stack
    */
   private final Stack<VariableInstance> executionStack = new Stack<VariableInstance>();

   /**
    * default ctor
    */
   public Scope() {
      types = new Types();
      /*
       * add the known atomic types
       */
      types.addType("integer", new IntegerType());
      types.addType("char", new CharType());
      types.addType("boolean", new BooleanType());
      types.addType("real", new RealType());
      types.addType("string", new StringType());
   }

   /**
    * copy ctor
    */
   public Scope(Scope ctx) {
      /*
       * copy variables
       */
      ctx.variables.putAll(variables);
      /*
       * copy types
       */
      types = new Types(ctx.types);
   }

   public Stack<VariableInstance> getExecutionStack() {
      return executionStack;
   }

   public Types getTypes() {
      return types;
   }

   public Hashtable<String, VariableInstance> getVariables() {
      return variables;
   }

   public void reportVariables() {
      for (final String key : variables.keySet()) {
         System.out.println("\t" + key + " " + variables.get(key).getValue().getType().getClass().getName());
      }
   }
}
