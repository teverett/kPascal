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
import com.khubla.kpascal.listener.type.ArrayTypeListener;
import com.khubla.kpascal.listener.type.FileTypeListener;
import com.khubla.kpascal.listener.type.RecordTypeListener;
import com.khubla.kpascal.listener.type.SetTypeListener;
import com.khubla.kpascal.type.Type;
import com.khubla.pascal.pascalParser;

public class UnpackedStructuredTypeListener extends AbstractPascalListener {
   private Type type = null;

   public UnpackedStructuredTypeListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterUnpackedStructuredType(pascalParser.UnpackedStructuredTypeContext ctx) {
      if (null != ctx.arrayType()) {
         /*
          * array
          */
         final ArrayTypeListener arrayTypeListener = new ArrayTypeListener(getExecutionContext());
         arrayTypeListener.enterArrayType(ctx.arrayType());
         type = arrayTypeListener.getType();
      } else if (null != ctx.recordType()) {
         /*
          * recordx
          */
         final RecordTypeListener recordTypeListener = new RecordTypeListener(getExecutionContext());
         recordTypeListener.enterRecordType(ctx.recordType());
         type = recordTypeListener.getType();
      } else if (null != ctx.setType()) {
         /*
          * set
          */
         final SetTypeListener setTypeListener = new SetTypeListener(getExecutionContext());
         setTypeListener.enterSetType(ctx.setType());
         type = setTypeListener.getType();
      } else if (null != ctx.fileType()) {
         /*
          * file
          */
         final FileTypeListener fileTypeListener = new FileTypeListener(getExecutionContext());
         fileTypeListener.enterFileType(ctx.fileType());
         type = fileTypeListener.getType();
      }
   }

   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }
}
