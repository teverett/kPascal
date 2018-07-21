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
package com.khubla.kpascal.type;

import com.khubla.kpascal.value.IntegerValue;
import com.khubla.kpascal.value.Value;

public class SubrangeType implements Type {
   public IntegerValue lowerRange;
   public IntegerValue upperRange;

   public SubrangeType(IntegerValue lowerRange, IntegerValue upperRange) {
      this.upperRange = upperRange;
      this.lowerRange = lowerRange;
   }

   @Override
   public boolean builtIn() {
      return true;
   }

   @Override
   public Value createValue() {
      return null;
   }
}
