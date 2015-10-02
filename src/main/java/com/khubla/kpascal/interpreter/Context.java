package com.khubla.kpascal.interpreter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Stack;

import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.type.SimpleType;
import com.khubla.kpascal.value.SimpleValue;

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
    * input stream
    */
   private final InputStream stdIn;
   /**
    * output stream
    */
   private final OutputStream stdOut;
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

   /**
    * ctor
    */
   public Context(InputStream stdIn, OutputStream stdOut) {
      this.stdIn = stdIn;
      this.stdOut = stdOut;
   }

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

   public InputStream getStdIn() {
      return stdIn;
   }

   public OutputStream getStdOut() {
      return stdOut;
   }

   /**
    * resolve a string to a value
    */
   public SimpleValue resolve(String v) throws InterpreterException {
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
         return (SimpleValue) constants.get(v).getValue();
      }
      /*
       * is variable?
       */
      if (null != getCurrentScope().getVariables().get(v)) {
         return (SimpleValue) getCurrentScope().getVariables().get(v).getValue();
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
}
