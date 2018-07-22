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

import java.util.ArrayList;
import java.util.List;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser;
import com.khubla.pascal.pascalParser.ExpressionContext;

public class ElementListener extends AbstractPascalListener {
   private final List<Value> values = new ArrayList<Value>();

   public ElementListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterElement(pascalParser.ElementContext ctx) {
      if (null != ctx.expression()) {
         for (final ExpressionContext expressionContext : ctx.expression()) {
            final ExpressionListener expressionListener = new ExpressionListener(getExecutionContext());
            expressionListener.enterExpression(expressionContext);
            values.add(expressionListener.getValue());
         }
      }
   }

   public List<Value> getValues() {
      return values;
   }
}
