package com.khubla.kpascal.value;

import com.khubla.kpascal.type.SimpleType;
import com.khubla.kpascal.type.Type;

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
public class SimpleValue implements Value {
   private final SimpleType simpleType;
   private final String value;

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

   public Boolean asBoolean() throws NumberFormatException {
      return Boolean.valueOf(value);
   }

   public Float asFloat() throws NumberFormatException {
      return Float.parseFloat(value);
   }

   public Integer asInteger() throws NumberFormatException {
      return Integer.parseInt(value);
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
}
