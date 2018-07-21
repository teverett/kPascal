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
import com.khubla.kpascal.exception.NotImplementedException;
import com.khubla.pascal.pascalParser;
import com.khubla.pascal.pascalParser.VariantContext;

public class VariantPartListener extends AbstractPascalListener {
   public VariantPartListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterVariantPart(pascalParser.VariantPartContext ctx) {
      if (null != ctx.tag()) {
         final TagListener tagListener = new TagListener(getExecutionContext());
         tagListener.enterTag(ctx.tag());
         if (null != ctx.variant()) {
            for (final VariantContext variantContext : ctx.variant()) {
               final VariantListener variantListener = new VariantListener(getExecutionContext());
               variantListener.enterVariant(variantContext);
            }
         }
      }
      throw new NotImplementedException();
   }

   @Override
   public void exitVariantPart(pascalParser.VariantPartContext ctx) {
   }
}
