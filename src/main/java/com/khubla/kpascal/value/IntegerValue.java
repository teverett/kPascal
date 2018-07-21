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

import com.khubla.kpascal.exception.InvalidOperationException;
import com.khubla.kpascal.type.IntegerType;
import com.khubla.kpascal.type.Type;

public class IntegerValue implements AtomicValue {
   public static final IntegerValue one = new IntegerValue(1);
   public static final IntegerValue negativeone = new IntegerValue(1);
   private final IntegerType type = new IntegerType();
   private int value;

   public IntegerValue() {
      value = 0;
   }

   public IntegerValue(int value) {
      this.value = value;
   }

   @Override
   public Value add(Value v) {
      if (v instanceof IntegerValue) {
         return new IntegerValue(value + ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new RealValue(value + ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
      }
   }

   @Override
   public Value and(Value v) {
      throw new InvalidOperationException();
   }

   public void decrement() {
      value = value - 1;
   }

   @Override
   public Value deepCopy() {
      return new IntegerValue(value);
   }

   @Override
   public Value div(Value v) {
      if (v instanceof IntegerValue) {
         return new RealValue((double) value / ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new RealValue(value / ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
      }
   }

   @Override
   public BooleanValue eq(Value v) {
      if (v instanceof IntegerValue) {
         return new BooleanValue(value == ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new BooleanValue(value == ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
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
   public BooleanValue gt(Value v) {
      if (v instanceof IntegerValue) {
         return new BooleanValue(value > ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new BooleanValue(value > ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
      }
   }

   @Override
   public BooleanValue gte(Value v) {
      if (v instanceof IntegerValue) {
         return new BooleanValue(value >= ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new BooleanValue(value >= ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
      }
   }

   @Override
   public Value idiv(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue in(Value v) {
      throw new InvalidOperationException();
   }

   public void increment() {
      value = value + 1;
   }

   @Override
   public BooleanValue lt(Value v) {
      if (v instanceof IntegerValue) {
         return new BooleanValue(value < ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new BooleanValue(value < ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
      }
   }

   @Override
   public BooleanValue lte(Value v) {
      if (v instanceof IntegerValue) {
         return new BooleanValue(value <= ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new BooleanValue(value <= ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
      }
   }

   @Override
   public Value mod(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value mult(Value v) {
      if (v instanceof IntegerValue) {
         return new IntegerValue(value * ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new RealValue(value * ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
      }
   }

   @Override
   public Value neg() {
      return new IntegerValue(value * -1);
   }

   @Override
   public BooleanValue neq(Value v) {
      if (v instanceof IntegerValue) {
         return new BooleanValue(value != ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new BooleanValue(value != ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
      }
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
      if (v instanceof IntegerValue) {
         value = ((IntegerValue) v).getValue();
      } else {
         throw new InvalidOperationException();
      }
   }

   @Override
   public void setFromString(String s) {
      value = Integer.parseInt(s);
   }

   public void setValue(int value) {
      this.value = value;
   }

   @Override
   public Value subtract(Value v) {
      if (v instanceof IntegerValue) {
         return new IntegerValue(value - ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new RealValue(value - ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
      }
   }
}
