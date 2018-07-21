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
import com.khubla.kpascal.type.RealType;
import com.khubla.kpascal.type.Type;

public class RealValue implements AtomicValue {
   private final RealType type = new RealType();
   private double value;

   public RealValue() {
      value = 0;
   }

   public RealValue(double value) {
      this.value = value;
   }

   @Override
   public Value add(Value v) {
      if (v instanceof IntegerValue) {
         return new RealValue(value + ((IntegerValue) v).getValue());
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

   @Override
   public Value div(Value v) {
      if (v instanceof IntegerValue) {
         return new RealValue(value / ((IntegerValue) v).getValue());
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
      return Double.toString(value);
   }

   @Override
   public Type getType() {
      return type;
   }

   public double getValue() {
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
         return new RealValue(value * ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new RealValue(value * ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
      }
   }

   @Override
   public Value neg() {
      return new RealValue(value * -1);
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
   public Value or(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public void set(Value v) {
      if (v instanceof RealValue) {
         value = ((RealValue) v).getValue();
      } else {
         throw new InvalidOperationException();
      }
   }

   @Override
   public void setFromString(String s) {
      value = Double.parseDouble(s);
   }

   public void setValue(double value) {
      this.value = value;
   }

   @Override
   public Value subtract(Value v) {
      if (v instanceof IntegerValue) {
         return new RealValue(value * ((IntegerValue) v).getValue());
      } else if (v instanceof RealValue) {
         return new RealValue(value * ((RealValue) v).getValue());
      } else {
         throw new InvalidOperationException();
      }
   }
}
