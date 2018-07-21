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
import com.khubla.pascal.pascalParser.ElementContext;

public class ElementListListener extends AbstractPascalListener {
   private final List<List<Value>> elementList = new ArrayList<List<Value>>();

   public ElementListListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterElementList(pascalParser.ElementListContext ctx) {
      if (null != ctx.element()) {
         for (final ElementContext elementContext : ctx.element()) {
            final ElementListener elementListener = new ElementListener(getExecutionContext());
            elementListener.enterElement(elementContext);
            elementList.add(elementListener.getValues());
         }
      }
   }

   @Override
   public void exitElementList(pascalParser.ElementListContext ctx) {
   }

   public List<List<Value>> getElementList() {
      return elementList;
   }
}
