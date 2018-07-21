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
package com.khubla.kpascal.runtime.function.io;

import java.util.List;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.kpascal.runtime.function.AbstractRuntimeFunction;
import com.khubla.kpascal.value.AtomicValue;
import com.khubla.kpascal.value.Value;

public class ReadlnFunction extends AbstractRuntimeFunction {
   @Override
   public Value execute(ExecutionContext executionContext, List<Value> args) {
      if (null != args) {
         for (final Value v : args) {
            if (v instanceof AtomicValue) {
               try {
                  final String s = executionContext.getConsoleInput().readLine();
                  ((AtomicValue) v).setFromString(s);
               } catch (final Exception e) {
                  throw new RuntimeException(e);
               }
            } else {
               throw new RuntimeException("Expected atomic value");
            }
         }
      }
      return null;
   }
}
