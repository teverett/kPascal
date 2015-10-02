package com.khubla.kpascal.value;

import com.khubla.kpascal.type.PointerType;
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
public class PointerValue implements Value {

	private final PointerType pointerType;

	public PointerValue(PointerType pointerType) {
		this.pointerType = pointerType;
	}

	@Override
	public Type getType() {
		return this.pointerType;
	}

	public PointerType getPointerType() {
		return pointerType;
	}

}
