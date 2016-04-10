package com.khubla.kpascal.type;

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
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hashtable of all known types
 *
 * @author tom
 */
public class Types {
   /**
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   /**
    * types
    */
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
         logger.debug("Nameless type");
      }
   }

   public Type find(String name) {
      return types.get(name.toLowerCase());
   }

   public Set<String> keys() {
      return types.keySet();
   }

   public void resolveComponentTypes() {
      for (final Type type : types.values()) {
         {
            if (type instanceof ArrayType) {
               final ArrayType arrayType = (ArrayType) type;
               final Type containedType = find(arrayType.getComponentTypeName());
               if (null != containedType) {
                  arrayType.setComponentType(containedType);
               } else {
                  logger.info("Unable to find type '" + arrayType.getComponentTypeName() + "'");
               }
            } else if (type instanceof PointerType) {
               final PointerType pointerType = (PointerType) type;
               final Type containedType = find(pointerType.getComponentTypeName());
               if (null != containedType) {
                  pointerType.setComponentType(containedType);
               } else {
                  logger.info("Unable to find type '" + pointerType.getComponentTypeName() + "'");
               }
            } else if (type instanceof RecordType) {
               final RecordType recordType = (RecordType) type;
               final Enumeration<String> keys = recordType.getFieldTypeNames().keys();
               while (keys.hasMoreElements()) {
                  final String id = keys.nextElement();
                  final String typeName = recordType.getFieldTypeNames().get(id);
                  final Type containedType = find(typeName);
                  if (null != containedType) {
                     recordType.getFields().put(id, containedType);
                  } else {
                     logger.info("Unable to find type '" + typeName + "'");
                  }
               }
            }
         }
      }
   }

   public int size() {
      return types.size();
   }
}
