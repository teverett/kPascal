package com.khubla.kpascal.interpreter.visitor;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.interpreter.Procedure;
import com.khubla.kpascal.interpreter.ProcedureArgument;
import com.khubla.kpascal.type.Type;

public class ProcedureVisitor extends PascalBaseVisitor<Void> {
   private final Context context;
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   private Procedure procedure = null;

   public ProcedureVisitor(Context context) {
      this.context = context;
   }

   public Context getContext() {
      return context;
   }

   @Override
   public Void visitFormalParameterSection(PascalParser.FormalParameterSectionContext ctx) {
      /*
       * var parameters?
       */
      String typeName;
      String parameterNames;
      boolean var;
      if (ctx.getChildCount() == 2) {
         var = true;
         typeName = ctx.getChild(1).getChild(2).getText();
         parameterNames = ctx.getChild(1).getChild(0).getText();
      } else {
         var = false;
         typeName = ctx.getChild(0).getChild(2).getText();
         parameterNames = ctx.getChild(0).getChild(0).getText();
      }
      final String[] parameterNamesArray = parameterNames.split(",");
      final Type type = context.getScopeStack().getCurrentScope().getTypes().find(typeName);
      if (null != type) {
         for (final String parameterName : parameterNamesArray) {
            final ProcedureArgument procedureArgument = new ProcedureArgument(parameterName, type, var);
            procedure.addArgument(procedureArgument);
         }
      } else {
         logger.info("Unknown type '" + typeName + "'");
      }
      return visitChildren(ctx);
   }

   @Override
   public Void visitFunctionDeclaration(PascalParser.FunctionDeclarationContext ctx) {
      final String name = ctx.getChild(1).getText();
      final PascalParser.BlockContext blockContext = (PascalParser.BlockContext) ctx.getChild(ctx.getChildCount() - 1);
      final String resultTypeString = ctx.getChild(ctx.getChildCount() - 3).getText();
      final Type type = context.getScopeStack().getCurrentScope().getTypes().find(resultTypeString);
      procedure = new Procedure(name, blockContext, type);
      context.getProcedures().put(name, procedure);
      return visitChildren(ctx);
   }

   @Override
   public Void visitProcedureDeclaration(PascalParser.ProcedureDeclarationContext ctx) {
      final String name = ctx.getChild(1).getText();
      final PascalParser.BlockContext blockContext = (PascalParser.BlockContext) ctx.getChild(ctx.getChildCount() - 1);
      procedure = new Procedure(name, blockContext, null);
      context.getProcedures().put(name, procedure);
      return visitChildren(ctx);
   }
}
