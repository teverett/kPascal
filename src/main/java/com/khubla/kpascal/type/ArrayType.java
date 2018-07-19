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
package com.khubla.kpascal.type;

import java.util.List;

import com.khubla.kpascal.value.ArrayValue;
import com.khubla.kpascal.value.Value;

public class ArrayType implements Type {
   public List<Type> indices;
   private Type componentType;

   public ArrayType(Type componentType, List<Type> indices) {
      this.componentType = componentType;
      this.indices = indices;
   }

   @Override
   public boolean builtIn() {
      return false;
   }

   @Override
   public Value createValue() {
      return new ArrayValue(this);
   }

   public Type getComponentType() {
      return componentType;
   }

   public List<Type> getIndices() {
      return indices;
   }

   public void setComponentType(Type componentType) {
      this.componentType = componentType;
   }

   public void setIndices(List<Type> indices) {
      this.indices = indices;
   }
}
