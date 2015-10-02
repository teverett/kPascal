package com.khubla.kpascal.value;

import com.khubla.kpascal.type.SetType;
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
public class SetValue implements Value {

	private final SetType setType;

	public SetValue(SetType setType) {
		this.setType = setType;
	}

	public SetType getSetType() {
		return setType;
	}

	@Override
	public Type getType() {
		return this.setType;
	}

}
