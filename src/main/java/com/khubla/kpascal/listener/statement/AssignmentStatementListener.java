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
import com.khubla.kpascal.listener.ExpressionListener;
import com.khubla.kpascal.listener.VariableListener;
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser;

public class AssignmentStatementListener extends AbstractPascalListener {
   public AssignmentStatementListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterAssignmentStatement(pascalParser.AssignmentStatementContext ctx) {
      if (null != ctx.expression()) {
         final ExpressionListener expressionListener = new ExpressionListener(getExecutionContext());
         expressionListener.enterExpression(ctx.expression());
         if (null != ctx.variable()) {
            final VariableListener variableListener = new VariableListener(getExecutionContext());
            variableListener.enterVariable(ctx.variable());
            final Value value = variableListener.getValue();
            try {
               value.set(expressionListener.getValue());
            } catch (final Exception e) {
               throw new RuntimeException(e);
            }
         }
      }
   }

   @Override
   public void exitAssignmentStatement(pascalParser.AssignmentStatementContext ctx) {
   }
}
