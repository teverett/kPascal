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
import com.khubla.kpascal.listener.AbstractkPascalListener;
import com.khubla.pascal.pascalParser;

public class StructuredStatementListener extends AbstractkPascalListener {
   public StructuredStatementListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterStructuredStatement(pascalParser.StructuredStatementContext ctx) {
      if (null != ctx.compoundStatement()) {
         final CompoundStatementListener compoundStatementListener = new CompoundStatementListener(getExecutionContext());
         compoundStatementListener.enterCompoundStatement(ctx.compoundStatement());
      } else if (null != ctx.conditionalStatement()) {
         final ConditionalStatementListener conditionalStatementListener = new ConditionalStatementListener(getExecutionContext());
         conditionalStatementListener.enterConditionalStatement(ctx.conditionalStatement());
      } else if (null != ctx.withStatement()) {
         final WithStatementListener withStatementListener = new WithStatementListener(getExecutionContext());
         withStatementListener.enterWithStatement(ctx.withStatement());
      } else if (null != ctx.repetetiveStatement()) {
         final RepetetiveStatementListener repetetiveStatementListener = new RepetetiveStatementListener(getExecutionContext());
         repetetiveStatementListener.enterRepetetiveStatement(ctx.repetetiveStatement());
      }
   }

   @Override
   public void exitStructuredStatement(pascalParser.StructuredStatementContext ctx) {
   }
}
