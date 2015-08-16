package com.khubla.kpascal.type;

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

   public Types() {
      /*
       * add the known atomic types
       */
      addType("integer", new IntegerType());
      addType("char", new CharType());
      addType("boolean", new BooleanType());
      addType("real", new RealType());
      addType("string", new StringType());
   }

   public void addType(String name, Type type) {
      types.put(name, type);
   }

   public Type find(String name) {
      return types.get(name);
   }
}
