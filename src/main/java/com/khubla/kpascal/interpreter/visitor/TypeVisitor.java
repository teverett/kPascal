package com.khubla.kpascal.interpreter.visitor;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.type.ArrayType;
import com.khubla.kpascal.type.FileType;
import com.khubla.kpascal.type.PointerType;
import com.khubla.kpascal.type.RecordType;
import com.khubla.kpascal.type.SetType;
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
	private String containedTypeName = null;

	public String getContainedTypeName() {
		return containedTypeName;
	}

	public void setContainedTypeName(String containedTypeName) {
		this.containedTypeName = containedTypeName;
	}

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
		context.getCurrentScope().getTypes().addType(this.typeName, type);
		return visitChildren(ctx);
	}

	@Override
	public Type visitFileType(PascalParser.FileTypeContext ctx) {
		type = new FileType();
		context.getCurrentScope().getTypes().addType(this.typeName, type);
		return visitChildren(ctx);
	}

	@Override
	public Type visitRecordType(PascalParser.RecordTypeContext ctx) {
		type = new RecordType();
		context.getCurrentScope().getTypes().addType(this.typeName, type);
		return visitChildren(ctx);
	}

	@Override
	public Type visitSetType(PascalParser.SetTypeContext ctx) {
		type = new SetType();
		context.getCurrentScope().getTypes().addType(this.typeName, type);
		return visitChildren(ctx);
	}

	@Override
	public Type visitSubrangeType(PascalParser.SubrangeTypeContext ctx) {
		try {
			if ((null != type) && (type instanceof ArrayType)) {
				ArrayType.Range range = new ArrayType.Range();
				final ArrayType arrayType = (ArrayType) type;
				arrayType.ranges.add(range);
				final String lowerRange = ctx.getChild(0).getText();
				final String upperRange = ctx.getChild(2).getText();
				range.lowerRange = context.resolve(lowerRange);
				range.upperRange = context.resolve(upperRange);
			}
		} catch (final Exception e) {
			e.printStackTrace();
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

	@Override
	public Type visitComponentType(PascalParser.ComponentTypeContext ctx) {
		containedTypeName = ctx.getChild(0).getText();
		if (null != type)
			if ((type instanceof ArrayType)) {
				final ArrayType arrayType = (ArrayType) type;
				arrayType.setComponentTypeName(containedTypeName);
			}
		return visitChildren(ctx);
	}

	@Override
	public Type visitPointerType(PascalParser.PointerTypeContext ctx) {
		PointerType pointerType = new PointerType();
		pointerType.setComponentTypeName(ctx.getChild(1).getText());
		type = pointerType;
		context.getCurrentScope().getTypes().addType(this.typeName, type);
		return visitChildren(ctx);
	}

	@Override
	public Type visitType(PascalParser.TypeContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Type visitRecordSection(PascalParser.RecordSectionContext ctx) {
		String name = ctx.getChild(0).getText();
		String ttype = ctx.getChild(2).getText();
		if ((type instanceof RecordType)) {
			final RecordType recordType = (RecordType) type;
			recordType.getFieldTypeNames().put(name, ttype);
		}
		return visitChildren(ctx);
	}

}
