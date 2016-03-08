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
import java.util.ArrayList;
import java.util.List;

import com.khubla.kpascal.type.ArrayType;
import com.khubla.kpascal.type.Type;

public class ArrayValue implements Value {
   private final List<Value> values = new ArrayList<Value>();
   private final ArrayType arrayType;

   public ArrayValue(ArrayType arrayType) {
      this.arrayType = arrayType;
   }

   public ArrayType getArrayType() {
      return arrayType;
   }

   @Override
   public Type getType() {
      return arrayType;
   }

   public List<Value> getValues() {
      return values;
   }
}
