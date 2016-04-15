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
import com.khubla.kpascal.interpreter.VariableInstance;
import com.khubla.kpascal.type.Type;
import com.khubla.kpascal.value.Value;

public class VariableVisitor extends PascalBaseVisitor<Void> {
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   private final Context context;

   public VariableVisitor(Context context) {
      this.context = context;
   }

   public Context getContext() {
      return context;
   }

   // TODO, for array types, what we need here is to loop over every index.
   // So, if we have x[1][3] we need to loop over each index, so that values of the first index are arrays and
   // the values of the second index are whatever the array type is
   @Override
   public Void visitVariableDeclaration(PascalParser.VariableDeclarationContext ctx) {
      final String instanceNames = ctx.getChild(0).getText();
      final String[] instanceNamesArray = instanceNames.split(",");
      final String typeName = ctx.getChild(2).getText();
      final Type type = context.getCurrentScope().getTypes().find(typeName);
      if (null != type) {
         for (final String instanceName : instanceNamesArray) {
            final Value val = type.createValue();
            final VariableInstance v = new VariableInstance(instanceName, val, VariableInstance.VariableDeclarationType.variable);
            context.getCurrentScope().addVariable(instanceName, v);
         }
      } else {
         logger.info("Unknown type '" + typeName + "'");
      }
      return visitChildren(ctx);
   }
}
