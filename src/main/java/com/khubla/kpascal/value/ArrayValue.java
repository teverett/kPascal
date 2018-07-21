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
package com.khubla.kpascal.value;

import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.type.ArrayType;
import com.khubla.kpascal.type.Type;

/**
 * note that Pascal arrays index from 1, not 0
 */
public class ArrayValue implements Value {
   /**
    * values
    */
   private final Value[] values;
   /**
    * size
    */
   private final int size;
   /**
    * lower bound
    */
   private final int lbound;
   /**
    * upper bound
    */
   private final int ubound;
   /**
    * type
    */
   private final ArrayType arrayType;

   public ArrayValue(ArrayType arrayType) {
      this.arrayType = arrayType;
      lbound = arrayType.indices.get(0).lowerRange.getValue();
      ubound = arrayType.indices.get(0).upperRange.getValue();
      size = (ubound - lbound) + 1;
      values = new Value[size];
      for (int i = 0; i < size; i++) {
         values[i] = arrayType.getComponentType().createValue();
      }
   }

   @Override
   public Value add(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   @Override
   public Value div(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   public ArrayType getArrayType() {
      return arrayType;
   }

   @Override
   public Type getType() {
      return arrayType;
   }

   public Value getValue(int idx) throws InterpreterException {
      if ((idx >= lbound) && (idx <= ubound)) {
         return values[idx - 1];
      } else {
         throw new InterpreterException("Index '" + idx + "' out of range for array of size '" + size + "'");
      }
   }

   @Override
   public Value mult(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   @Override
   public Value neg() throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   @Override
   public void set(Value v) throws InterpreterException {
      throw new RuntimeException("Invalid operation");
   }

   public void setValue(int idx, Value value) throws InterpreterException {
      if ((idx >= lbound) && (idx <= ubound)) {
         values[idx - 1] = value;
      } else {
         throw new InterpreterException("Index '" + idx + "' out of range for array of size '" + size + "'");
      }
   }

   @Override
   public Value subtract(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }
}
