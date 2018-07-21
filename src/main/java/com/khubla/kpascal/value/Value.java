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

import com.khubla.kpascal.type.Type;

public interface Value {
   Value add(Value v);

   Value and(Value v);

   Value div(Value v);

   BooleanValue eq(Value v);

   Type getType();

   BooleanValue gt(Value v);

   BooleanValue gte(Value v);

   Value idiv(Value v);

   BooleanValue in(Value v);

   BooleanValue lt(Value v);

   BooleanValue lte(Value v);

   Value mod(Value v);

   Value mult(Value v);

   Value neg();

   BooleanValue neq(Value v);

   Value not();

   Value or(Value v);

   void set(Value v);

   Value subtract(Value v);
}
