package com.khubla.kpascal.interpreter.visitor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.rtl.RTLFunction;
import com.khubla.kpascal.rtl.RTLFunctions;
import com.khubla.kpascal.value.SimpleValue;

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
   private final Logger logger = LoggerFactory.getLogger(this.getClass());

   public ProgramVisitor(Context context) {
      this.context = context;
   }

   /**
    * get a parameter list
    */
   private List<String> getArguments(PascalParser.ParameterListContext ctx) {
      final List<String> ret = new ArrayList<String>();
      for (int i = 0; i < ctx.getChildCount(); i++) {
         final String parameter = ctx.getChild(i).getText();
         ret.add(parameter);
      }
      return ret;
   }

   public Context getContext() {
      return context;
   }

   /**
    * invoke RTL function
    */
   private void invokeRTLFunction(RTLFunction rtlFunction, List<String> arguments) throws InterpreterException {
      final List<SimpleValue> argumentValues = new ArrayList<SimpleValue>();
      if (null != arguments) {
         for (final String argument : arguments) {
            final SimpleValue v = context.resolveStringToValue(argument);
            argumentValues.add(v);
         }
      }
      rtlFunction.invoke(context, argumentValues);
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
             * find the function
             */
            final RTLFunction rtlFunction = RTLFunctions.getInstance().getRTLFunction(procedureName);
            if (null != rtlFunction) {
               /*
                * invoke RTL function
                */
               if (ctx.getChildCount() > 1) {
                  final PascalParser.ParameterListContext ctx2 = (PascalParser.ParameterListContext) ctx.getChild(2);
                  final List<String> arguments = getArguments(ctx2);
                  invokeRTLFunction(rtlFunction, arguments);
               } else {
                  invokeRTLFunction(rtlFunction, null);
               }
            } else {
               logger.error("Unab;e to find procedure '" + procedureName + "'");
            }
         }
      } catch (final Exception e) {
         e.printStackTrace();
      }
      return visitChildren(ctx);
   }
}
