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
package com.khubla.kpascal.listener.part;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.kpascal.listener.AbstractkPascalListener;
import com.khubla.kpascal.listener.ConstantDefinitionListener;
import com.khubla.pascal.pascalParser;
import com.khubla.pascal.pascalParser.ConstantDefinitionContext;

public class ConstantDefinitionPartListener extends AbstractkPascalListener {
   public ConstantDefinitionPartListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterConstantDefinitionPart(pascalParser.ConstantDefinitionPartContext ctx) {
      if (null != ctx.constantDefinition()) {
         for (final ConstantDefinitionContext constantDefinitionContext : ctx.constantDefinition()) {
            final ConstantDefinitionListener constantDefinitionListener = new ConstantDefinitionListener(getExecutionContext());
            constantDefinitionListener.enterConstantDefinition(constantDefinitionContext);
         }
      }
   }

   @Override
   public void exitConstantDefinitionPart(pascalParser.ConstantDefinitionPartContext ctx) {
   }
}
