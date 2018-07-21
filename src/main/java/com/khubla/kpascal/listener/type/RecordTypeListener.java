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
package com.khubla.kpascal.listener.type;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.kpascal.exception.NotImplementedException;
import com.khubla.kpascal.listener.AbstractPascalListener;
import com.khubla.kpascal.listener.FieldListListener;
import com.khubla.kpascal.type.Type;
import com.khubla.pascal.pascalParser;

public class RecordTypeListener extends AbstractPascalListener {
   private Type type;

   public RecordTypeListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterRecordType(pascalParser.RecordTypeContext ctx) {
      if (null != ctx.fieldList()) {
         final FieldListListener fieldListListener = new FieldListListener(getExecutionContext());
         fieldListListener.enterFieldList(ctx.fieldList());
      }
      throw new NotImplementedException();
   }

   @Override
   public void exitRecordType(pascalParser.RecordTypeContext ctx) {
   }

   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }
}
