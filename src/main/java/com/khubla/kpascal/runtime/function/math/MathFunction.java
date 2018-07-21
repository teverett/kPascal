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
package com.khubla.kpascal.runtime.function.math;

import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.runtime.function.AbstractRuntimeFunction;
import com.khubla.kpascal.value.IntegerValue;
import com.khubla.kpascal.value.RealValue;
import com.khubla.kpascal.value.Value;

public abstract class MathFunction extends AbstractRuntimeFunction {
   protected double getDouble(Value value) {
      if (value instanceof IntegerValue) {
         return ((IntegerValue) value).getValue();
      } else if (value instanceof RealValue) {
         return ((RealValue) value).getValue();
      } else {
         throw new InterpreterException("Invalid value");
      }
   }
}
