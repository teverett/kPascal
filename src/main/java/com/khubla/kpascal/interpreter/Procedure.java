package com.khubla.kpascal.interpreter;

import java.util.ArrayList;
import java.util.List;

import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.type.Type;
import com.khubla.kpascal.value.Value;

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
 * A procedure is implemented as, simply, a reference to a parse tree. When the procedure is invoked, the parse tree is parsed. There is a specific difference in that a procedure has its own context
 * object
 *
 * @author tom
 */
public class Procedure {
   private final PascalParser.BlockContext procedureBlockContext;
   private final List<ProcedureArgument> arguments = new ArrayList<ProcedureArgument>();
   private final String name;
   /**
    * if the returnType is null, this is a procedure. Otherwise, it's a function.
    */
   private final Type returnType;

   public Procedure(String name, PascalParser.BlockContext procedureBlockContext, Type returnType) {
      this.procedureBlockContext = procedureBlockContext;
      this.name = name;
      this.returnType = returnType;
   }

   public void addArgument(ProcedureArgument procedureArgument) {
      arguments.add(procedureArgument);
   }

   public List<ProcedureArgument> getArguments() {
      return arguments;
   }

   public String getName() {
      return name;
   }

   public PascalParser.BlockContext getProcedureBlockContext() {
      return procedureBlockContext;
   }

   public Type getReturnType() {
      return returnType;
   }

   public void invoke(Context context, List<Value> parameters) {
   }
}
