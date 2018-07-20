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
import com.khubla.kpascal.listener.IdentifierListener;
import com.khubla.kpascal.listener.StatementListener;
import com.khubla.kpascal.value.SimpleValue;
import com.khubla.pascal.pascalParser;

public class ForStatementListener extends AbstractkPascalListener {
   public ForStatementListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterForStatement(pascalParser.ForStatementContext ctx) {
      if (null != ctx.identifier()) {
         final IdentifierListener identifierListener = new IdentifierListener(getExecutionContext());
         identifierListener.enterIdentifier(ctx.identifier());
         if (null != ctx.forList()) {
            final ForListListener forListListener = new ForListListener(getExecutionContext());
            forListListener.enterForList(ctx.forList());
            /*
             * set the initial value
             */
            SimpleValue indexValue = new SimpleValue(forListListener.getInitialValue().asInteger());
            getExecutionContext().getCurrentStackframe().declareVariable(identifierListener.getIdentifier(), indexValue);
            boolean loop = true;
            while (loop) {
               if (null != ctx.statement()) {
                  final StatementListener statementListener = new StatementListener(getExecutionContext());
                  statementListener.enterStatement(ctx.statement());
                  /*
                   * increment
                   */
                  try {
                     indexValue = SimpleValue.add(indexValue, new SimpleValue(1));
                  } catch (final Exception e) {
                     throw new RuntimeException(e);
                  }
                  /*
                   * check
                   */
                  loop = (indexValue.asInteger() < forListListener.getFinalValue().asInteger());
               }
            }
         }
      }
   }

   @Override
   public void exitForStatement(pascalParser.ForStatementContext ctx) {
   }
}
