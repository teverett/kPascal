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
import com.khubla.kpascal.listener.ParameterGroupListener.Parameter;
import com.khubla.pascal.pascalParser;
import com.khubla.pascal.pascalParser.FormalParameterSectionContext;

public class FormalParameterListListener extends AbstractPascalListener {
   private final List<Parameter> parameters = new ArrayList<Parameter>();

   public FormalParameterListListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterFormalParameterList(pascalParser.FormalParameterListContext ctx) {
      if (null != ctx.formalParameterSection()) {
         for (final FormalParameterSectionContext formalParameterSectionContext : ctx.formalParameterSection()) {
            final FormalParameterSectionListener formalParameterSectionListener = new FormalParameterSectionListener(getExecutionContext());
            formalParameterSectionListener.enterFormalParameterSection(formalParameterSectionContext);
            parameters.addAll(formalParameterSectionListener.getParameters());
         }
      }
   }

   public List<Parameter> getParameters() {
      return parameters;
   }
}
