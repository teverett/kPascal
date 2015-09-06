package com.khubla.kpascal.interpreter;

import com.khubla.kpascal.antlr.PascalParser;

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
   private final PascalParser.ProcedureDeclarationContext procedureDeclarationContext;

   public Procedure(PascalParser.ProcedureDeclarationContext procedureDeclarationContext) {
      this.procedureDeclarationContext = procedureDeclarationContext;
   }

   public PascalParser.ProcedureDeclarationContext getProcedureDeclarationContext() {
      return procedureDeclarationContext;
   }
}
