package com.khubla.kpascal.interpreter.visitor;

import org.antlr.v4.runtime.ParserRuleContext;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.interpreter.Value;
import com.khubla.kpascal.interpreter.VariableInstance;
import com.khubla.kpascal.type.SimpleType;
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
public class ConstantVisitor extends PascalBaseVisitor<Void> {
   private final Context context;

   public ConstantVisitor(Context context) {
      this.context = context;
   }

   public Context getContext() {
      return context;
   }

   @Override
   public Void visitConstantDefinition(PascalParser.ConstantDefinitionContext ctx) {
      final String name = ctx.getChild(0).getText();
      final String value = ctx.getChild(2).getText();
      final ParserRuleContext parserRuleContext = (ParserRuleContext) ctx.getChild(2).getChild(0).getChild(0);
      VariableInstance v = null;
      Type type = null;
      if (parserRuleContext instanceof PascalParser.UnsignedRealContext) {
         type = new SimpleType(SimpleType.Type.real);
      } else if (parserRuleContext instanceof PascalParser.UnsignedIntegerContext) {
         type = new SimpleType(SimpleType.Type.integer);
      } else if (parserRuleContext instanceof PascalParser.StringContext) {
         type = new SimpleType(SimpleType.Type.string);
      }
      final Value val = new Value(type, value);
      v = new VariableInstance(name, val, VariableInstance.VariableDeclarationType.constant);
      context.getConstants().put(name, v);
      return visitChildren(ctx);
   }
}
