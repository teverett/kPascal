package com.khubla.kpascal.interpreter.visitor;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.type.ArrayType;
import com.khubla.kpascal.type.Type;

/*
* kPascal Copyright 2015, khubla.com
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
public class TypeVisitor extends PascalBaseVisitor<Type> {
   private final Context context;
   private Type type = null;
   private String typeName = null;

   public TypeVisitor(Context context) {
      this.context = context;
   }

   public Context getContext() {
      return context;
   }

   public Type getType() {
      return type;
   }

   public String getTypeName() {
      return typeName;
   }

   public void setType(Type type) {
      this.type = type;
   }

   public void setTypeName(String typeName) {
      this.typeName = typeName;
   }

   @Override
   public Type visitArrayType(PascalParser.ArrayTypeContext ctx) {
      type = new ArrayType();
      return visitChildren(ctx);
   }

   @Override
   public Type visitFileType(PascalParser.FileTypeContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public Type visitRecordType(PascalParser.RecordTypeContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public Type visitSetType(PascalParser.SetTypeContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public Type visitSubrangeType(PascalParser.SubrangeTypeContext ctx) {
      if ((null != type) && (type instanceof ArrayType)) {
         final ArrayType arrayType = (ArrayType) type;
         final String lowerRange = ctx.getChild(0).getText();
         if (null != context.getConstants().get(lowerRange)) {
            arrayType.setLowerRange(Integer.parseInt(context.getConstants().get(lowerRange).getValue()));
         } else {
            arrayType.setLowerRange(Integer.parseInt(lowerRange));
         }
         final String upperRange = ctx.getChild(2).getText();
         if (null != context.getConstants().get(upperRange)) {
            arrayType.setUpperRange(Integer.parseInt(context.getConstants().get(upperRange).getValue()));
         } else {
            arrayType.setUpperRange(Integer.parseInt(upperRange));
         }
      }
      return visitChildren(ctx);
   }

   @Override
   public Type visitTypeDefinition(PascalParser.TypeDefinitionContext ctx) {
      typeName = ctx.getChild(0).getText();
      return visitChildren(ctx);
   }

   @Override
   public Type visitTypeList(PascalParser.TypeListContext ctx) {
      return visitChildren(ctx);
   }
}
