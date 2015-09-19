package com.khubla.kpascal.interpreter.visitor;

import org.antlr.v4.runtime.ParserRuleContext;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.interpreter.Scope;
import com.khubla.kpascal.interpreter.VariableInstance;
import com.khubla.kpascal.type.IntegerType;
import com.khubla.kpascal.type.RealType;
import com.khubla.kpascal.type.StringType;
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
public class VariableVisitor extends PascalBaseVisitor<VariableInstance> {
   private final Scope scope;

   public VariableVisitor(Scope scope) {
      this.scope = scope;
   }

   public Scope getScope() {
      return scope;
   }

   @Override
   public VariableInstance visitConstantDefinition(PascalParser.ConstantDefinitionContext ctx) {
      final String name = ctx.getChild(0).getText();
      final String value = ctx.getChild(2).getText();
      final ParserRuleContext parserRuleContext = (ParserRuleContext) ctx.getChild(2).getChild(0).getChild(0);
      VariableInstance v = null;
      Type type = null;
      if (parserRuleContext instanceof PascalParser.UnsignedRealContext) {
         type = new RealType();
      } else if (parserRuleContext instanceof PascalParser.UnsignedIntegerContext) {
         type = new IntegerType();
      } else if (parserRuleContext instanceof PascalParser.StringContext) {
         type = new StringType();
      }
      v = new VariableInstance(name, type, VariableInstance.VariableDeclarationType.constant, value);
      scope.getVariables().put(name, v);
      return visitChildren(ctx);
   }

   @Override
   public VariableInstance visitVariableDeclaration(PascalParser.VariableDeclarationContext ctx) {
      final String instanceName = ctx.getChild(0).getText();
      final String typeName = ctx.getChild(2).getText();
      final Type type = scope.getTypes().find(typeName);
      if (null != type) {
         final VariableInstance v = new VariableInstance(instanceName, type, VariableInstance.VariableDeclarationType.variable, null);
         scope.getVariables().put(instanceName, v);
      } else {
         System.out.println("Unknown type '" + typeName + "'");
      }
      return visitChildren(ctx);
   }
}
