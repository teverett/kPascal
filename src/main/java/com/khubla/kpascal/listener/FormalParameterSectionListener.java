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
import com.khubla.kpascal.listener.ParameterGroupListener.ParameterGroup;
import com.khubla.kpascal.listener.ParameterGroupListener.ParameterType;
import com.khubla.pascal.pascalParser;

public class FormalParameterSectionListener extends AbstractPascalListener {
   private ParameterGroup parameterGroup;

   public FormalParameterSectionListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterFormalParameterSection(pascalParser.FormalParameterSectionContext ctx) {
      if (null != ctx.parameterGroup()) {
         final ParameterGroupListener parameterGroupListener = new ParameterGroupListener(getExecutionContext());
         parameterGroupListener.enterParameterGroup(ctx.parameterGroup());
         parameterGroup = parameterGroupListener.getParameterGroup();
      }
      if (null != ctx.VAR()) {
         parameterGroup.setParameterType(ParameterType.var);
      } else if (null != ctx.PROCEDURE()) {
         parameterGroup.setParameterType(ParameterType.procedure);
      } else if (null != ctx.FUNCTION()) {
         parameterGroup.setParameterType(ParameterType.function);
      }
   }

   @Override
   public void exitFormalParameterSection(pascalParser.FormalParameterSectionContext ctx) {
   }

   public ParameterGroup getParameterGroup() {
      return parameterGroup;
   }

   public void setParameterGroup(ParameterGroup parameterGroup) {
      this.parameterGroup = parameterGroup;
   }
}
