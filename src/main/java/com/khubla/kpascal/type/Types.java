package com.khubla.kpascal.type;

import java.util.Enumeration;
import java.util.Hashtable;

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
/**
 * Hashtable of all known types
 *
 * @author tom
 */
public class Types {
	private final Hashtable<String, Type> types = new Hashtable<String, Type>();

	/**
	 * default ctor
	 */
	public Types() {
	}

	/**
	 * copy ctor
	 */
	public Types(Types types) {
		types.types.putAll(this.types);
	}

	public void addType(String name, Type type) {
		if (null != name) {
			types.put(name.toLowerCase(), type);
		} else {
			System.out.println("Nameless type");
		}
	}

	public Type find(String name) {
		return types.get(name.toLowerCase());
	}

	public void resolveComponentTypes() {
		for (Type type : types.values()) {
			{
				if (type instanceof ArrayType) {
					ArrayType arrayType = (ArrayType) type;
					Type containedType = this.find(arrayType.getComponentTypeName());
					if (null != containedType) {
						arrayType.setComponentType(containedType);
					} else {
						System.out.println("Unable to find type '" + arrayType.getComponentTypeName() + "'");
					}
				} else if (type instanceof PointerType) {
					PointerType pointerType = (PointerType) type;
					Type containedType = this.find(pointerType.getComponentTypeName());
					if (null != containedType) {
						pointerType.setComponentType(containedType);
					} else {
						System.out.println("Unable to find type '" + pointerType.getComponentTypeName() + "'");
					}
				} else if (type instanceof RecordType) {
					RecordType recordType = (RecordType) type;
					Enumeration<String> keys = recordType.getFieldTypeNames().keys();
					while (keys.hasMoreElements()) {
						String id = keys.nextElement();
						String typeName = recordType.getFieldTypeNames().get(id);
						Type containedType = this.find(typeName);
						if (null != containedType) {
							recordType.getFields().put(id, containedType);
						} else {
							System.out.println("Unable to find type '" + typeName + "'");
						}
					}

				}
			}
		}
	}
}
