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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.kpascal.type.SimpleType;
import com.khubla.kpascal.type.Types;
import com.khubla.kpascal.value.Value;

public class Scope {
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
      types.addType("integer", new SimpleType(SimpleType.Type.integer));
      types.addType("char", new SimpleType(SimpleType.Type.character));
      types.addType("boolean", new SimpleType(SimpleType.Type.bool));
      types.addType("real", new SimpleType(SimpleType.Type.real));
      types.addType("string", new SimpleType(SimpleType.Type.string));
   }

   public Hashtable<String, VariableInstance> getVariables() {
      return variables;
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

   public void addVariable(String name, VariableInstance variableInstance) {
      variables.put(name.toLowerCase(), variableInstance);
   }

   /**
    * find a variable in the scope
    */
   public VariableInstance findVariable(String name) {
      return variables.get(name.toLowerCase());
   }

   public Stack<VariableInstance> getExecutionStack() {
      return executionStack;
   }

   public Types getTypes() {
      return types;
   }

   public void reportVariables() {
      for (final String key : variables.keySet()) {
         final Value v = variables.get(key).getValue();
         if (null != v) {
            logger.info("\t" + key + " " + v.getType().getClass().getName());
         }
      }
   }
}
