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

import java.util.Hashtable;

import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.type.RecordType;
import com.khubla.kpascal.type.Type;

public class RecordValue implements Value {
   private final RecordType recordType;
   private final Hashtable<String, Value> fieldValues = new Hashtable<String, Value>();

   public RecordValue(RecordType recordType) {
      this.recordType = recordType;
   }

   @Override
   public Value add(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   @Override
   public Value div(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   public Hashtable<String, Value> getFieldValues() {
      return fieldValues;
   }

   public RecordType getRecordType() {
      return recordType;
   }

   @Override
   public Type getType() {
      return recordType;
   }

   @Override
   public Value mult(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   @Override
   public Value neg() throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   @Override
   public void set(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   @Override
   public Value subtract(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }
}
