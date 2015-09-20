package com.khubla.kpascal.interpreter;

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
public class Value {
   private final Type type;
   private final String value;

   /**
    * ctor for boolean
    */
   public Value(Boolean value) {
      type = new SimpleType(SimpleType.Type.bool);
      this.value = Boolean.toString(value);
   }

   /**
    * ctor for char
    */
   public Value(char value) {
      type = new SimpleType(SimpleType.Type.character);
      this.value = Character.toString(value);
   }

   /**
    * ctor for double
    */
   public Value(Double value) {
      type = new SimpleType(SimpleType.Type.real);
      this.value = Double.toString(value);
   }

   /**
    * ctor for integer
    */
   public Value(Integer value) {
      type = new SimpleType(SimpleType.Type.integer);
      this.value = Integer.toString(value);
   }

   /**
    * ctor for string
    */
   public Value(String value) {
      type = new SimpleType(SimpleType.Type.string);
      this.value = value;
   }

   public Value(Type type, String value) {
      this.type = type;
      this.value = value;
   }

   public Type getType() {
      return type;
   }

   public String getValue() {
      return value;
   }
}
