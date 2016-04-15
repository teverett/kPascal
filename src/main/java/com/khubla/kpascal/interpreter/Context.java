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

import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.type.SimpleType;
import com.khubla.kpascal.value.SimpleValue;
import com.khubla.kpascal.value.Value;

/**
 * The context is the execution context for the entire program. It contains all global state
 * information about the program including the stack of execution scopes, all global constants and
 * every procedure
 *
 * @author teverett
 */
public class Context {
   /**
    * all procedures by name
    */
   private final Hashtable<String, Procedure> procedures = new Hashtable<String, Procedure>();
   /**
    * scope stack
    */
   private final ScopeStack scopeStack = new ScopeStack();
   /**
    * constants
    */
   private final Hashtable<String, VariableInstance> constants = new Hashtable<String, VariableInstance>();

   /**
    * ctor
    */
   public Context() {
   }

   public Hashtable<String, VariableInstance> getConstants() {
      return constants;
   }

   public Hashtable<String, Procedure> getProcedures() {
      return procedures;
   }

   public ScopeStack getScopeStack() {
      return scopeStack;
   }

   /**
    * Resolve a string to a value.
    * <p>
    * Not to be used for composite strings like a[i], but to be used by atomic strings like "a".
    * </p>
    */
   public Value resolveStringToValue(String v) throws InterpreterException {
      /*
       * string
       */
      if (v.startsWith("\'") && (v.endsWith("\'"))) {
         return new SimpleValue(new SimpleType(SimpleType.Type.string), v.substring(1, v.length() - 1));
      }
      /*
       * is a constant?
       */
      if (null != constants.get(v)) {
         return constants.get(v).getValue();
      }
      /*
       * is variable?
       */
      if (null != getScopeStack().getCurrentScope().findVariable(v)) {
         return getScopeStack().getCurrentScope().findVariable(v).getValue();
      }
      /*
       * is integer?
       */
      try {
         return new SimpleValue(Integer.parseInt(v));
      } catch (final NumberFormatException e) {
         // do nothing
      }
      /*
       * is real?
       */
      try {
         return new SimpleValue(Double.parseDouble(v));
      } catch (final NumberFormatException e) {
         // do nothing
      }
      /*
       * is boolean?
       */
      try {
         return new SimpleValue(Boolean.parseBoolean(v));
      } catch (final NumberFormatException e) {
         // do nothing
      }
      /*
       * nope
       */
      throw new InterpreterException("Unable to resolve '" + v + "'");
   }

   /**
    * given a name, walk the scope stack, looking for a matching variable
    */
   public VariableInstance resolveVariableInstance(String name) {
      for (int i = 0; i < scopeStack.size(); i++) {
         final Scope scope = scopeStack.get(i);
         final VariableInstance variableInstance = scope.findVariable(name);
         if (null != variableInstance) {
            return variableInstance;
         }
      }
      return null;
   }
}
