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

import java.util.ArrayList;
import java.util.List;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.kpascal.listener.AbstractPascalListener;
import com.khubla.kpascal.listener.LabelListener;
import com.khubla.kpascal.value.IntegerValue;
import com.khubla.pascal.pascalParser;
import com.khubla.pascal.pascalParser.LabelContext;

public class LabelDeclarationPartListener extends AbstractPascalListener {
   private final List<IntegerValue> values = new ArrayList<IntegerValue>();

   public LabelDeclarationPartListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterLabelDeclarationPart(pascalParser.LabelDeclarationPartContext ctx) {
      if (null != ctx.label()) {
         for (final LabelContext labelContext : ctx.label()) {
            final LabelListener labelListener = new LabelListener(getExecutionContext());
            labelListener.enterLabel(labelContext);
            values.add(labelListener.getValue());
         }
      }
   }

   @Override
   public void exitLabelDeclarationPart(pascalParser.LabelDeclarationPartContext ctx) {
   }

   public List<IntegerValue> getValues() {
      return values;
   }
}
