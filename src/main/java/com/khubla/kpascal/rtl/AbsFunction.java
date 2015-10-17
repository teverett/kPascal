package com.khubla.kpascal.rtl;

import java.util.List;

import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.type.SimpleType;
import com.khubla.kpascal.value.SimpleValue;
import com.khubla.kpascal.value.Value;

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
public class AbsFunction extends BaseRTLFunction {
   @Override
   public Value invoke(Context context, List<Value> argumentValues) throws InterpreterException {
      if ((null != argumentValues) && (argumentValues.size() == 1) && (argumentValues.get(0) instanceof SimpleValue)) {
         final SimpleValue simpleValue = (SimpleValue) argumentValues.get(0);
         if (simpleValue.getSimpleType().getType() == SimpleType.Type.integer) {
            final int i = simpleValue.asInteger();
            return new SimpleValue(Math.abs(i));
         } else if (simpleValue.getSimpleType().getType() == SimpleType.Type.real) {
            final double f = simpleValue.asFloat();
            return new SimpleValue(Math.abs(f));
         } else {
            throw new InterpreterException("Invalid Parameter '" + simpleValue.toString() + "'");
         }
      } else {
         throw new InterpreterException("Invalid Parameter");
      }
   }
}
