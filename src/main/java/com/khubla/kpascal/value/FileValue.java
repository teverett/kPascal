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
import com.khubla.kpascal.type.FileType;
import com.khubla.kpascal.type.Type;

public class FileValue implements Value {
   private final FileType fileType;

   public FileValue(FileType fileType) {
      this.fileType = fileType;
   }

   @Override
   public SimpleValue add(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   @Override
   public SimpleValue div(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   public FileType getFileType() {
      return fileType;
   }

   @Override
   public Type getType() {
      return fileType;
   }

   @Override
   public SimpleValue mult(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   @Override
   public SimpleValue neg() throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }

   @Override
   public SimpleValue subtract(Value v) throws InterpreterException {
      throw new InterpreterException("Invalid operation");
   }
}
