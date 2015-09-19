package com.khubla.kpascal.interpreter.visitor;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.interpreter.Procedure;

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
public class ProcedureVisitor extends PascalBaseVisitor<Void> {
   private final Context context;

   public ProcedureVisitor(Context context) {
      this.context = context;
   }

   public Context getContext() {
      return context;
   }

   @Override
   public Void visitProcedureDeclaration(PascalParser.ProcedureDeclarationContext ctx) {
      final String name = ctx.getChild(1).getText();
      context.getProcedures().put(name, new Procedure(ctx));
      return visitChildren(ctx);
   }
}
