/*
 * kPascal Copyright 2018, Tom Everett
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
package com.khubla.kpascal.listener.statement;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.kpascal.listener.AbstractPascalListener;
import com.khubla.kpascal.listener.StatementListener;
import com.khubla.pascal.pascalParser;

public class WhileStatementListener extends AbstractPascalListener {
   public WhileStatementListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterWhileStatement(pascalParser.WhileStatementContext ctx) {
      if (null != ctx.statement()) {
         if (null != ctx.expression()) {
            boolean c = getExecutionContext().testExpression(ctx.expression());
            while (true == c) {
               final StatementListener statementListener = new StatementListener(getExecutionContext());
               statementListener.enterStatement(ctx.statement());
               c = getExecutionContext().testExpression(ctx.expression());
            }
         }
      }
   }
}
