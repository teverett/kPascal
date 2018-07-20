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
import com.khubla.kpascal.value.ArrayValue;
import com.khubla.kpascal.value.SimpleValue;
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser;
import com.khubla.pascal.pascalParser.ExpressionContext;

public class VariableListener extends AbstractkPascalListener {
   /**
    * not sure that this is the right way.....
    */
   private Value value;

   public VariableListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterVariable(pascalParser.VariableContext ctx) {
      if (null != ctx.identifier()) {
         final List<Value> indices = new ArrayList<Value>();
         final IdentifierListener identifierListener = new IdentifierListener(getExecutionContext());
         identifierListener.enterIdentifier(ctx.identifier(0));
         if (null != ctx.expression()) {
            for (final ExpressionContext expressionContext : ctx.expression()) {
               final ExpressionListener expressionListener = new ExpressionListener(getExecutionContext());
               expressionListener.enterExpression(expressionContext);
               indices.add(expressionListener.getValue());
            }
         }
         final String variableName = identifierListener.getIdentifier();
         value = getExecutionContext().resolveVariable(variableName);
         if (indices.size() > 0) {
            for (int i = 0; i < indices.size(); i++) {
               if (value instanceof ArrayValue) {
                  final ArrayValue av = (ArrayValue) value;
                  final SimpleValue sv = (SimpleValue) indices.get(i);
                  try {
                     value = av.getValue(sv.asInteger());
                  } catch (final Exception e) {
                     throw new RuntimeException(e);
                  }
               } else {
                  throw new RuntimeException("cannot index into non-array type");
               }
            }
         }
      }
   }

   @Override
   public void exitVariable(pascalParser.VariableContext ctx) {
   }

   public Value getValue() {
      return value;
   }

   public void setValue(Value value) {
      this.value = value;
   }
}
