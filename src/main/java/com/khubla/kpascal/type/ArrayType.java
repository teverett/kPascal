package com.khubla.kpascal.type;

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

import com.khubla.kpascal.value.ArrayValue;
import com.khubla.kpascal.value.SimpleValue;
import com.khubla.kpascal.value.Value;

public class ArrayType implements Type {
   public static class Range {
      public SimpleValue lowerRange;
      public SimpleValue upperRange;
   };

   public List<Range> ranges = new ArrayList<Range>();
   private Type componentType;
   private String componentTypeName;

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

   public String getComponentTypeName() {
      return componentTypeName;
   }

   public List<Range> getRanges() {
      return ranges;
   }

   public void setComponentType(Type componentType) {
      this.componentType = componentType;
   }

   public void setComponentTypeName(String componentTypeName) {
      this.componentTypeName = componentTypeName;
   }

   public void setRanges(List<Range> ranges) {
      this.ranges = ranges;
   }
}
