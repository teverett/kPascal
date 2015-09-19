// Generated from com/khubla/kpascal/antlr/Pascal.g4 by ANTLR 4.5.1
package com.khubla.kpascal.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced by {@link PascalParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for operations with no return type.
 */
public interface PascalVisitor<T> extends ParseTreeVisitor<T> {
   /**
    * Visit a parse tree produced by {@link PascalParser#actualParameter}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitActualParameter(PascalParser.ActualParameterContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#arrayType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitArrayType(PascalParser.ArrayTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#assignmentStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitAssignmentStatement(PascalParser.AssignmentStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#baseType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitBaseType(PascalParser.BaseTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#block}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitBlock(PascalParser.BlockContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#caseListElement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitCaseListElement(PascalParser.CaseListElementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#caseStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitCaseStatement(PascalParser.CaseStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#componentType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitComponentType(PascalParser.ComponentTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#compoundStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitCompoundStatement(PascalParser.CompoundStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#conditionalStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitConditionalStatement(PascalParser.ConditionalStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#constant}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitConstant(PascalParser.ConstantContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#constantChr}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitConstantChr(PascalParser.ConstantChrContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#constantDefinition}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitConstantDefinition(PascalParser.ConstantDefinitionContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#constantDefinitionPart}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitConstantDefinitionPart(PascalParser.ConstantDefinitionPartContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#constList}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitConstList(PascalParser.ConstListContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#element}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitElement(PascalParser.ElementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#elementList}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitElementList(PascalParser.ElementListContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#empty}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitEmpty(PascalParser.EmptyContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#emptyStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitEmptyStatement(PascalParser.EmptyStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#expression}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitExpression(PascalParser.ExpressionContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#factor}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitFactor(PascalParser.FactorContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#fieldList}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitFieldList(PascalParser.FieldListContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#fileType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitFileType(PascalParser.FileTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#finalValue}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitFinalValue(PascalParser.FinalValueContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#fixedPart}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitFixedPart(PascalParser.FixedPartContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#forList}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitForList(PascalParser.ForListContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#formalParameterList}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitFormalParameterList(PascalParser.FormalParameterListContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#formalParameterSection}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitFormalParameterSection(PascalParser.FormalParameterSectionContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#forStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitForStatement(PascalParser.ForStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#functionDeclaration}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitFunctionDeclaration(PascalParser.FunctionDeclarationContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#functionDesignator}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitFunctionDesignator(PascalParser.FunctionDesignatorContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#functionType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitFunctionType(PascalParser.FunctionTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#gotoStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitGotoStatement(PascalParser.GotoStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#identifier}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitIdentifier(PascalParser.IdentifierContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#identifierList}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitIdentifierList(PascalParser.IdentifierListContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#ifStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitIfStatement(PascalParser.IfStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#indexType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitIndexType(PascalParser.IndexTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#initialValue}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitInitialValue(PascalParser.InitialValueContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#label}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitLabel(PascalParser.LabelContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#labelDeclarationPart}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitLabelDeclarationPart(PascalParser.LabelDeclarationPartContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#parameterGroup}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitParameterGroup(PascalParser.ParameterGroupContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#parameterList}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitParameterList(PascalParser.ParameterListContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#pointerType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitPointerType(PascalParser.PointerTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#procedureAndFunctionDeclarationPart}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitProcedureAndFunctionDeclarationPart(PascalParser.ProcedureAndFunctionDeclarationPartContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#procedureDeclaration}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitProcedureDeclaration(PascalParser.ProcedureDeclarationContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#procedureOrFunctionDeclaration}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitProcedureOrFunctionDeclaration(PascalParser.ProcedureOrFunctionDeclarationContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#procedureStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitProcedureStatement(PascalParser.ProcedureStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#procedureType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitProcedureType(PascalParser.ProcedureTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#program}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitProgram(PascalParser.ProgramContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#programHeading}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitProgramHeading(PascalParser.ProgramHeadingContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#recordSection}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitRecordSection(PascalParser.RecordSectionContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#recordType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitRecordType(PascalParser.RecordTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#recordVariableList}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitRecordVariableList(PascalParser.RecordVariableListContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#repeatStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitRepeatStatement(PascalParser.RepeatStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#repetetiveStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitRepetetiveStatement(PascalParser.RepetetiveStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#resultType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitResultType(PascalParser.ResultTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#scalarType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitScalarType(PascalParser.ScalarTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#set}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitSet(PascalParser.SetContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#setType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitSetType(PascalParser.SetTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#sign}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitSign(PascalParser.SignContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#signedFactor}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitSignedFactor(PascalParser.SignedFactorContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#simpleExpression}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitSimpleExpression(PascalParser.SimpleExpressionContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#simpleStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitSimpleStatement(PascalParser.SimpleStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#simpleType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitSimpleType(PascalParser.SimpleTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#statement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitStatement(PascalParser.StatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#statements}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitStatements(PascalParser.StatementsContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#string}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitString(PascalParser.StringContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#stringtype}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitStringtype(PascalParser.StringtypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#structuredStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitStructuredStatement(PascalParser.StructuredStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#structuredType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitStructuredType(PascalParser.StructuredTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#subrangeType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitSubrangeType(PascalParser.SubrangeTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#tag}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitTag(PascalParser.TagContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#term}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitTerm(PascalParser.TermContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#type}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitType(PascalParser.TypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#typeDefinition}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitTypeDefinition(PascalParser.TypeDefinitionContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#typeDefinitionPart}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitTypeDefinitionPart(PascalParser.TypeDefinitionPartContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#typeIdentifier}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitTypeIdentifier(PascalParser.TypeIdentifierContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#typeList}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitTypeList(PascalParser.TypeListContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#unlabelledStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitUnlabelledStatement(PascalParser.UnlabelledStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#unpackedStructuredType}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitUnpackedStructuredType(PascalParser.UnpackedStructuredTypeContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#unsignedConstant}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitUnsignedConstant(PascalParser.UnsignedConstantContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#unsignedInteger}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitUnsignedInteger(PascalParser.UnsignedIntegerContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#unsignedNumber}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitUnsignedNumber(PascalParser.UnsignedNumberContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#unsignedReal}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitUnsignedReal(PascalParser.UnsignedRealContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#usesUnitsPart}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitUsesUnitsPart(PascalParser.UsesUnitsPartContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#variable}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitVariable(PascalParser.VariableContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#variableDeclaration}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitVariableDeclaration(PascalParser.VariableDeclarationContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#variableDeclarationPart}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitVariableDeclarationPart(PascalParser.VariableDeclarationPartContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#variant}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitVariant(PascalParser.VariantContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#variantPart}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitVariantPart(PascalParser.VariantPartContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#whileStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitWhileStatement(PascalParser.WhileStatementContext ctx);

   /**
    * Visit a parse tree produced by {@link PascalParser#withStatement}.
    *
    * @param ctx the parse tree
    * @return the visitor result
    */
   T visitWithStatement(PascalParser.WithStatementContext ctx);
}