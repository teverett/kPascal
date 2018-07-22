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
import com.khubla.kpascal.type.RecordType;
import com.khubla.kpascal.type.RecordType.Field;
import com.khubla.pascal.pascalParser;

public class RecordSectionListener extends AbstractPascalListener {
   private final List<Field> fields = new ArrayList<Field>();

   public RecordSectionListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterRecordSection(pascalParser.RecordSectionContext ctx) {
      if (null != ctx.identifierList()) {
         final IdentifierListListener identifierListListener = new IdentifierListListener(getExecutionContext());
         identifierListListener.enterIdentifierList(ctx.identifierList());
         if (null != ctx.type()) {
            final TypeListener typeListener = new TypeListener(getExecutionContext());
            typeListener.enterType(ctx.type());
            /*
             * create fields
             */
            for (final String name : identifierListListener.getIdentifiers()) {
               final Field field = new RecordType.Field(name, typeListener.getType());
               fields.add(field);
            }
         }
      }
   }

   public List<Field> getFields() {
      return fields;
   }
}
