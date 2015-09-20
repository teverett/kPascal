package com.khubla.kpascal.interpreter.visitor;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.interpreter.Value;
import com.khubla.kpascal.interpreter.VariableInstance;
import com.khubla.kpascal.type.Type;

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
public class VariableVisitor extends PascalBaseVisitor<Void> {
   private final Context context;

   public VariableVisitor(Context context) {
      this.context = context;
   }

   public Context getContext() {
      return context;
   }

   @Override
   public Void visitVariableDeclaration(PascalParser.VariableDeclarationContext ctx) {
      final String instanceName = ctx.getChild(0).getText();
      final String typeName = ctx.getChild(2).getText();
      final Type type = context.getCurrentScope().getTypes().find(typeName);
      if (null != type) {
         final Value val = new Value(type, null);
         final VariableInstance v = new VariableInstance(instanceName, val, VariableInstance.VariableDeclarationType.variable);
         context.getCurrentScope().getVariables().put(instanceName, v);
      } else {
         System.out.println("Unknown type '" + typeName + "'");
      }
      return visitChildren(ctx);
   }
}
