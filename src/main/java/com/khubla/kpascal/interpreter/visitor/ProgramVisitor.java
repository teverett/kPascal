package com.khubla.kpascal.interpreter.visitor;

import java.util.ArrayList;
import java.util.List;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.interpreter.Value;
import com.khubla.kpascal.rtl.RTLFunction;
import com.khubla.kpascal.rtl.RTLFunctions;

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
public class ProgramVisitor extends PascalBaseVisitor<Void> {
   private final Context context;

   public ProgramVisitor(Context context) {
      this.context = context;
   }

   public Context getContext() {
      return context;
   }

   /**
    * get a parameter list
    */
   private List<String> getParameters(PascalParser.ParameterListContext ctx) {
      final List<String> ret = new ArrayList<String>();
      for (int i = 0; i < ctx.getChildCount(); i++) {
         final String parameter = ctx.getChild(i).getText();
         ret.add(parameter);
      }
      return ret;
   }

   /**
    * invoke RTL function
    */
   private void invokeRTLFunction(String functionName, List<String> parameters) throws InterpreterException {
      final List<Value> values = new ArrayList<Value>();
      if (null != parameters) {
         for (final String str : parameters) {
            final Value v = context.resolve(str);
            values.add(v);
         }
      }
      final RTLFunction function = RTLFunctions.getInstance().getRTLFunction(functionName);
      if (null != function) {
         function.invoke(context, values);
      }
   }

   @Override
   public Void visitBlock(PascalParser.BlockContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public Void visitProcedureStatement(PascalParser.ProcedureStatementContext ctx) {
      try {
         final String procedureName = ctx.getChild(0).getText();
         if (context.getProcedures().containsKey(procedureName)) {
            /*
             * invoke pascal procedure
             */
         } else {
            /*
             * invoke RTL function
             */
            if (ctx.getChildCount() > 1) {
               final PascalParser.ParameterListContext ctx2 = (PascalParser.ParameterListContext) ctx.getChild(2);
               final List<String> parameters = getParameters(ctx2);
               invokeRTLFunction(procedureName, parameters);
            } else {
               invokeRTLFunction(procedureName, null);
            }
         }
      } catch (final Exception e) {
         e.printStackTrace();
      }
      return visitChildren(ctx);
   }
}
