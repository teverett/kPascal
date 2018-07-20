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
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser;

public class FactorListener extends AbstractkPascalListener {
   private Value value;

   public FactorListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterFactor(pascalParser.FactorContext ctx) {
      if (null != ctx.variable()) {
         /*
          * variable
          */
         final VariableListener variableListener = new VariableListener(getExecutionContext());
         variableListener.enterVariable(ctx.variable());
         /*
          * resolve the variable
          */
         value = variableListener.getValue();
      } else if (null != ctx.NOT()) {
         final FactorListener factorListener = new FactorListener(getExecutionContext());
         factorListener.enterFactor(ctx.factor());
         /*
          * do math to NOT the factor
          */
         throw new RuntimeException("not implemented");
      } else if (null != ctx.unsignedConstant()) {
         /*
          * constant
          */
         final UnsignedConstantListener unsignedConstantListener = new UnsignedConstantListener(getExecutionContext());
         unsignedConstantListener.enterUnsignedConstant(ctx.unsignedConstant());
         value = unsignedConstantListener.getValue();
      } else if (null != ctx.expression()) {
         /*
          * expression
          */
         final ExpressionListener expressionListener = new ExpressionListener(getExecutionContext());
         expressionListener.enterExpression(ctx.expression());
         value = expressionListener.getValue();
      } else {
         throw new RuntimeException("not implemented");
      }
   }

   @Override
   public void exitFactor(pascalParser.FactorContext ctx) {
   }

   public Value getValue() {
      return value;
   }

   public void setValue(Value value) {
      this.value = value;
   }
}
