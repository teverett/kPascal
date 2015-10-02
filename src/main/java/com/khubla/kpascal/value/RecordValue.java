package com.khubla.kpascal.value;

import java.util.Hashtable;

import com.khubla.kpascal.type.RecordType;
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
public class RecordValue implements Value {

	private final RecordType recordType;
	private final Hashtable<String, Value> fieldValues = new Hashtable<String, Value>();

	public Hashtable<String, Value> getFieldValues() {
		return fieldValues;
	}

	public RecordValue(RecordType recordType) {
		this.recordType = recordType;
	}

	public RecordType getRecordType() {
		return recordType;
	}

	@Override
	public Type getType() {
		return this.recordType;
	}

}
