package com.khubla.kpascal.value;

import com.khubla.kpascal.exception.InterpreterException;
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
import com.khubla.kpascal.type.Type;

public interface Value {
   SimpleValue add(Value v) throws InterpreterException;

   SimpleValue div(Value v) throws InterpreterException;

   Type getType();

   SimpleValue mult(Value v) throws InterpreterException;

   SimpleValue neg() throws InterpreterException;

   SimpleValue subtract(Value v) throws InterpreterException;
}
