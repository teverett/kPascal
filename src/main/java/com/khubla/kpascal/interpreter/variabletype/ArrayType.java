package com.khubla.kpascal.interpreter.variabletype;

import com.khubla.kpascal.interpreter.VariableType;

/*
* kPascal Copyright 2012, khubla.com
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
public class ArrayType implements VariableType {
   private final VariableType containedType;
   private final int lowerIndex;
   private final int upperIndex;

   public ArrayType(VariableType containedType, int lowerIndex, int upperIndex) {
      this.containedType = containedType;
      this.lowerIndex = lowerIndex;
      this.upperIndex = upperIndex;
   }

   public VariableType getContainedType() {
      return containedType;
   }

   public int getLowerIndex() {
      return lowerIndex;
   }

   @Override
   public String getName() {
      return "Array";
   }

   public int getUpperIndex() {
      return upperIndex;
   }

   @Override
   public boolean isAtomicType() {
      return true;
   }
}
