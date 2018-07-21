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
import com.khubla.kpascal.type.IntegerType;
import com.khubla.kpascal.type.Type;

public class IntegerValue implements AtomicValue {
   private final IntegerType type = new IntegerType();
   private int value;

   public IntegerValue() {
      value = 0;
   }

   public IntegerValue(int value) {
      this.value = value;
   }

   @Override
   public Value add(Value v) throws InterpreterException {
      if (v instanceof IntegerValue) {
         return new IntegerValue(value + ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new RealValue(value + ((RealValue) v).getValue());
      } else {
         throw new InterpreterException("Invalid operation");
      }
   }

   @Override
   public Value div(Value v) throws InterpreterException {
      if (v instanceof IntegerValue) {
         return new RealValue(value / ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new RealValue(value / ((RealValue) v).getValue());
      } else {
         throw new InterpreterException("Invalid operation");
      }
   }

   @Override
   public String getAsString() {
      return Integer.toString(value);
   }

   @Override
   public Type getType() {
      return type;
   }

   public int getValue() {
      return value;
   }

   @Override
   public Value mult(Value v) throws InterpreterException {
      if (v instanceof IntegerValue) {
         return new IntegerValue(value * ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new RealValue(value * ((RealValue) v).getValue());
      } else {
         throw new InterpreterException("Invalid operation");
      }
   }

   @Override
   public Value neg() throws InterpreterException {
      return new IntegerValue(value * -1);
   }

   @Override
   public void set(Value v) throws InterpreterException {
      if (v instanceof IntegerValue) {
         value = ((IntegerValue) v).getValue();
      } else {
         throw new InterpreterException("Invalid operation");
      }
   }

   public void setValue(int value) {
      this.value = value;
   }

   @Override
   public Value subtract(Value v) throws InterpreterException {
      if (v instanceof IntegerValue) {
         return new IntegerValue(value - ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new RealValue(value - ((RealValue) v).getValue());
      } else {
         throw new InterpreterException("Invalid operation");
      }
   }
}
