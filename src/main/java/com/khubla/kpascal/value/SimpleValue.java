package com.khubla.kpascal.value;

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
import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.type.SimpleType;
import com.khubla.kpascal.type.Type;

public class SimpleValue implements Value {
   private SimpleType simpleType;
   private String value;

   /**
    * ctor for boolean
    */
   public SimpleValue(Boolean value) {
      simpleType = new SimpleType(SimpleType.Type.bool);
      this.value = Boolean.toString(value);
   }

   /**
    * ctor for char
    */
   public SimpleValue(char value) {
      simpleType = new SimpleType(SimpleType.Type.character);
      this.value = Character.toString(value);
   }

   /**
    * ctor for double
    */
   public SimpleValue(Double value) {
      simpleType = new SimpleType(SimpleType.Type.real);
      this.value = Double.toString(value);
   }

   /**
    * ctor for integer
    */
   public SimpleValue(Integer value) {
      simpleType = new SimpleType(SimpleType.Type.integer);
      this.value = Integer.toString(value);
   }

   public SimpleValue(SimpleType simpleType) {
      this.simpleType = simpleType;
      value = null;
   }

   public SimpleValue(SimpleType simpleType, String value) {
      this.simpleType = simpleType;
      this.value = value;
   }

   /**
    * ctor for string
    */
   public SimpleValue(String value) {
      simpleType = new SimpleType(SimpleType.Type.string);
      this.value = value;
   }

   @Override
   public SimpleValue add(Value v) throws InterpreterException {
      if (v instanceof SimpleValue) {
         final SimpleValue sv = (SimpleValue) v;
         if ((sv.isNumericType()) && (isNumericType())) {
            if ((getSimpleType().getType() == SimpleType.Type.integer) && (sv.getSimpleType().getType() == SimpleType.Type.integer)) {
               return new SimpleValue(asInteger() + sv.asInteger());
            } else {
               return new SimpleValue(asDouble() + sv.asDouble());
            }
         } else {
            throw new InterpreterException("Invalid operation");
         }
      } else {
         throw new InterpreterException("Invalid operation");
      }
   }

   public Boolean asBoolean() throws NumberFormatException {
      return Boolean.valueOf(value);
   }

   /**
    * not implemented
    *
    * @return
    * @throws NumberFormatException
    */
   public Integer asCharacter() throws NumberFormatException {
      throw new NumberFormatException();
   }

   public Double asDouble() throws NumberFormatException {
      return Double.parseDouble(value);
   }

   public Integer asInteger() throws NumberFormatException {
      return Integer.parseInt(value);
   }

   public String asString() throws NumberFormatException {
      return value;
   }

   @Override
   public SimpleValue div(Value v) throws InterpreterException {
      if (v instanceof SimpleValue) {
         final SimpleValue sv = (SimpleValue) v;
         if ((sv.isNumericType()) && (isNumericType())) {
            if ((getSimpleType().getType() == SimpleType.Type.integer) && (sv.getSimpleType().getType() == SimpleType.Type.integer)) {
               return new SimpleValue(asInteger() / sv.asInteger());
            } else {
               return new SimpleValue(asDouble() / sv.asDouble());
            }
         } else {
            throw new InterpreterException("Invalid operation");
         }
      } else {
         throw new InterpreterException("Invalid operation");
      }
   }

   public SimpleType getSimpleType() {
      return simpleType;
   }

   @Override
   public Type getType() {
      return simpleType;
   }

   public String getValue() {
      return value;
   }

   public boolean isNumericType() {
      if ((getSimpleType().getType() == SimpleType.Type.integer) || (getSimpleType().getType() == SimpleType.Type.real)) {
         return true;
      }
      return false;
   }

   @Override
   public SimpleValue mult(Value v) throws InterpreterException {
      if (v instanceof SimpleValue) {
         final SimpleValue sv = (SimpleValue) v;
         if ((sv.isNumericType()) && (isNumericType())) {
            if ((getSimpleType().getType() == SimpleType.Type.integer) && (sv.getSimpleType().getType() == SimpleType.Type.integer)) {
               return new SimpleValue(asInteger() * sv.asInteger());
            } else {
               return new SimpleValue(asDouble() * sv.asDouble());
            }
         } else {
            throw new InterpreterException("Invalid operation");
         }
      } else {
         throw new InterpreterException("Invalid operation");
      }
   }

   @Override
   public SimpleValue neg() throws InterpreterException {
      if (isNumericType()) {
         return new SimpleValue(asDouble() * -1);
      } else {
         throw new InterpreterException("Invalid operation");
      }
   }

   public void setValue(SimpleValue simpleValue) {
      simpleType = simpleValue.simpleType;
      value = simpleValue.value;
   }

   @Override
   public SimpleValue subtract(Value v) throws InterpreterException {
      if (v instanceof SimpleValue) {
         final SimpleValue sv = (SimpleValue) v;
         if ((sv.isNumericType()) && (isNumericType())) {
            if ((getSimpleType().getType() == SimpleType.Type.integer) && (sv.getSimpleType().getType() == SimpleType.Type.integer)) {
               return new SimpleValue(asInteger() - sv.asInteger());
            } else {
               return new SimpleValue(asDouble() - sv.asDouble());
            }
         } else {
            throw new InterpreterException("Invalid operation");
         }
      } else {
         throw new InterpreterException("Invalid operation");
      }
   }
}
