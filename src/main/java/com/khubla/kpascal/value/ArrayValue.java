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
import com.khubla.kpascal.exception.InvalidOperationException;
import com.khubla.kpascal.exception.NotImplementedException;
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
   public Value add(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value and(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value deepCopy() {
      throw new NotImplementedException();
   }

   @Override
   public Value div(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue eq(Value v) {
      throw new InvalidOperationException();
   }

   public ArrayType getArrayType() {
      return arrayType;
   }

   @Override
   public Type getType() {
      return arrayType;
   }

   public Value getValue(int idx) {
      if ((idx >= lbound) && (idx <= ubound)) {
         return values[idx - 1];
      } else {
         throw new InterpreterException("Index '" + idx + "' out of range for array of size '" + size + "'");
      }
   }

   @Override
   public BooleanValue gt(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue gte(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value idiv(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue in(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue lt(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue lte(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value mod(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value mult(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value neg() {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue neq(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value not() {
      throw new InvalidOperationException();
   }

   @Override
   public Value or(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public void set(Value v) {
      throw new InvalidOperationException();
   }

   public void setValue(int idx, Value value) {
      if ((idx >= lbound) && (idx <= ubound)) {
         values[idx - 1] = value;
      } else {
         throw new InterpreterException("Index '" + idx + "' out of range for array of size '" + size + "'");
      }
   }

   @Override
   public Value subtract(Value v) {
      throw new InvalidOperationException();
   }
}
