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

import com.khubla.kpascal.runtime.function.io.ReadFunction;
import com.khubla.kpascal.runtime.function.io.ReadlnFunction;
import com.khubla.kpascal.runtime.function.io.WriteFunction;
import com.khubla.kpascal.runtime.function.io.WritelnFunction;
import com.khubla.kpascal.runtime.function.math.AbsFunction;
import com.khubla.kpascal.runtime.function.math.ArcCosFunction;
import com.khubla.kpascal.runtime.function.math.ArcSinFunction;
import com.khubla.kpascal.runtime.function.math.ArcTanFunction;
import com.khubla.kpascal.runtime.function.math.CosFunction;
import com.khubla.kpascal.runtime.function.math.SinFunction;
import com.khubla.kpascal.runtime.function.math.SqrFunction;
import com.khubla.kpascal.runtime.function.math.SqrtFunction;
import com.khubla.kpascal.runtime.function.math.TanFunction;

public class RuntimeFunctionFactory {
   private final Hashtable<String, RuntimeFunction> functions = new Hashtable<String, RuntimeFunction>();

   public RuntimeFunctionFactory() {
      /*
       * io
       */
      addFunction("writeln", new WritelnFunction());
      addFunction("write", new WriteFunction());
      addFunction("read", new ReadFunction());
      addFunction("readln", new ReadlnFunction());
      /*
       * math
       */
      addFunction("sin", new SinFunction());
      addFunction("cos", new CosFunction());
      addFunction("tan", new TanFunction());
      addFunction("arcsin", new ArcSinFunction());
      addFunction("arccos", new ArcCosFunction());
      addFunction("arctan", new ArcTanFunction());
      addFunction("abs", new AbsFunction());
      addFunction("sqr", new SqrFunction());
      addFunction("sqrt", new SqrtFunction());
   }

   private void addFunction(String name, RuntimeFunction runtimeFunction) {
      functions.put(name.toLowerCase(), runtimeFunction);
   }

   public RuntimeFunction getRuntimeFunction(String name) {
      final RuntimeFunction runtimeFunction = functions.get(name.toLowerCase());
      if (null != runtimeFunction) {
         return runtimeFunction;
      } else {
         return null;
      }
   }
}
