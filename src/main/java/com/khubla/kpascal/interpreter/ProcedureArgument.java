package com.khubla.kpascal.interpreter;

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

public class ProcedureArgument {
   private final boolean var;
   private final String name;
   private final Type type;

   public ProcedureArgument(String name, Type type, boolean var) {
      this.var = var;
      this.name = name;
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public Type getType() {
      return type;
   }

   public boolean isVar() {
      return var;
   }
}
