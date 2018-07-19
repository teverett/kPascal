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
package com.khubla.kpascal.listener;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.pascal.pascalParser;

public class SimpleStatementListener extends AbstractkPascalListener {
   public SimpleStatementListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterSimpleStatement(pascalParser.SimpleStatementContext ctx) {
      if (null != ctx.assignmentStatement()) {
         final AssignmentStatementListener assignmentStatementListener = new AssignmentStatementListener(getExecutionContext());
         assignmentStatementListener.enterAssignmentStatement(ctx.assignmentStatement());
      } else if (null != ctx.procedureStatement()) {
         final ProcedureStatementListener procedureStatementListener = new ProcedureStatementListener(getExecutionContext());
         procedureStatementListener.enterProcedureStatement(ctx.procedureStatement());
      } else if (null != ctx.emptyStatement()) {
         final EmptyStatementListener emptyStatementListener = new EmptyStatementListener(getExecutionContext());
         emptyStatementListener.enterEmptyStatement(ctx.emptyStatement());
      } else if (null != ctx.gotoStatement()) {
         final GotoStatementListener gotoStatementListener = new GotoStatementListener(getExecutionContext());
         gotoStatementListener.enterGotoStatement(ctx.gotoStatement());
      } else {
         throw new RuntimeException("Unknown statement");
      }
   }

   @Override
   public void exitSimpleStatement(pascalParser.SimpleStatementContext ctx) {
   }
}
