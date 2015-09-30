package com.khubla.kpascal.rtl;

import java.io.PrintStream;
import java.util.List;

import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.interpreter.Value;

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
public class WritelnFunction implements RTLFunction {
	@Override
	public void invoke(Context context, List<Value> parameters) {
		if (null != parameters) {
			for (Value value : parameters) {
				new PrintStream(context.getStdOut()).print(value.getValue());
			}
		}

	}
}