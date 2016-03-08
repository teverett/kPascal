package com.khubla.kpascal.rtl;

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
import com.khubla.kpascal.interpreter.Invocable;

public class RTLFunctions {
   /**
    * instance
    */
   private static RTLFunctions instance = null;

   public static RTLFunctions getInstance() {
      if (null == instance) {
         instance = new RTLFunctions();
      }
      return instance;
   }

   /**
    * all functions
    */
   private final Hashtable<String, Class<? extends Invocable>> rtlFunctions = new Hashtable<String, Class<? extends Invocable>>();

   /**
    * ctor
    */
   private RTLFunctions() {
      rtlFunctions.put("writeln", WritelnFunction.class);
      rtlFunctions.put("write", WriteFunction.class);
      rtlFunctions.put("readln", ReadlnFunction.class);
      rtlFunctions.put("read", ReadFunction.class);
      rtlFunctions.put("new", NewFunction.class);
      rtlFunctions.put("dispose", DisposeFunction.class);
      rtlFunctions.put("abs", AbsFunction.class);
   }

   /**
    * find an RTL function
    */
   public Invocable getRTLFunction(String functionName) throws InterpreterException {
      try {
         final Class<? extends Invocable> clazz = rtlFunctions.get(functionName.toLowerCase());
         if (null != clazz) {
            return clazz.newInstance();
         } else {
            return null;
         }
      } catch (final Exception e) {
         throw new InterpreterException("Unable to get RTL Function '" + functionName + "'", e);
      }
   }
}
