package com.khubla.kpascal.interpreter;

/*
* kPascal Copyright 2012, khubla.com
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.khubla.kpascal.antlr.PascalLexer;
import com.khubla.kpascal.antlr.PascalListener;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.antlr.PascalParser.ActualParameterContext;
import com.khubla.kpascal.antlr.PascalParser.ArrayTypeContext;
import com.khubla.kpascal.antlr.PascalParser.AssignmentStatementContext;
import com.khubla.kpascal.antlr.PascalParser.BaseTypeContext;
import com.khubla.kpascal.antlr.PascalParser.BlockContext;
import com.khubla.kpascal.antlr.PascalParser.CaseListElementContext;
import com.khubla.kpascal.antlr.PascalParser.CaseStatementContext;
import com.khubla.kpascal.antlr.PascalParser.ComponentTypeContext;
import com.khubla.kpascal.antlr.PascalParser.CompoundStatementContext;
import com.khubla.kpascal.antlr.PascalParser.ConditionalStatementContext;
import com.khubla.kpascal.antlr.PascalParser.ConstListContext;
import com.khubla.kpascal.antlr.PascalParser.ConstantChrContext;
import com.khubla.kpascal.antlr.PascalParser.ConstantContext;
import com.khubla.kpascal.antlr.PascalParser.ConstantDefinitionContext;
import com.khubla.kpascal.antlr.PascalParser.ConstantDefinitionPartContext;
import com.khubla.kpascal.antlr.PascalParser.ElementContext;
import com.khubla.kpascal.antlr.PascalParser.ElementListContext;
import com.khubla.kpascal.antlr.PascalParser.EmptyContext;
import com.khubla.kpascal.antlr.PascalParser.EmptyStatementContext;
import com.khubla.kpascal.antlr.PascalParser.ExpressionContext;
import com.khubla.kpascal.antlr.PascalParser.FactorContext;
import com.khubla.kpascal.antlr.PascalParser.FieldListContext;
import com.khubla.kpascal.antlr.PascalParser.FileTypeContext;
import com.khubla.kpascal.antlr.PascalParser.FinalValueContext;
import com.khubla.kpascal.antlr.PascalParser.FixedPartContext;
import com.khubla.kpascal.antlr.PascalParser.ForListContext;
import com.khubla.kpascal.antlr.PascalParser.ForStatementContext;
import com.khubla.kpascal.antlr.PascalParser.FormalParameterListContext;
import com.khubla.kpascal.antlr.PascalParser.FormalParameterSectionContext;
import com.khubla.kpascal.antlr.PascalParser.FunctionDeclarationContext;
import com.khubla.kpascal.antlr.PascalParser.FunctionDesignatorContext;
import com.khubla.kpascal.antlr.PascalParser.FunctionTypeContext;
import com.khubla.kpascal.antlr.PascalParser.GotoStatementContext;
import com.khubla.kpascal.antlr.PascalParser.IdentifierContext;
import com.khubla.kpascal.antlr.PascalParser.IdentifierListContext;
import com.khubla.kpascal.antlr.PascalParser.IfStatementContext;
import com.khubla.kpascal.antlr.PascalParser.IndexTypeContext;
import com.khubla.kpascal.antlr.PascalParser.InitialValueContext;
import com.khubla.kpascal.antlr.PascalParser.LabelContext;
import com.khubla.kpascal.antlr.PascalParser.LabelDeclarationPartContext;
import com.khubla.kpascal.antlr.PascalParser.ParameterGroupContext;
import com.khubla.kpascal.antlr.PascalParser.ParameterListContext;
import com.khubla.kpascal.antlr.PascalParser.PointerTypeContext;
import com.khubla.kpascal.antlr.PascalParser.ProcedureAndFunctionDeclarationPartContext;
import com.khubla.kpascal.antlr.PascalParser.ProcedureDeclarationContext;
import com.khubla.kpascal.antlr.PascalParser.ProcedureOrFunctionDeclarationContext;
import com.khubla.kpascal.antlr.PascalParser.ProcedureStatementContext;
import com.khubla.kpascal.antlr.PascalParser.ProcedureTypeContext;
import com.khubla.kpascal.antlr.PascalParser.ProgramContext;
import com.khubla.kpascal.antlr.PascalParser.ProgramHeadingContext;
import com.khubla.kpascal.antlr.PascalParser.RecordSectionContext;
import com.khubla.kpascal.antlr.PascalParser.RecordTypeContext;
import com.khubla.kpascal.antlr.PascalParser.RecordVariableListContext;
import com.khubla.kpascal.antlr.PascalParser.RepeatStatementContext;
import com.khubla.kpascal.antlr.PascalParser.RepetetiveStatementContext;
import com.khubla.kpascal.antlr.PascalParser.ResultTypeContext;
import com.khubla.kpascal.antlr.PascalParser.ScalarTypeContext;
import com.khubla.kpascal.antlr.PascalParser.SetContext;
import com.khubla.kpascal.antlr.PascalParser.SetTypeContext;
import com.khubla.kpascal.antlr.PascalParser.SignContext;
import com.khubla.kpascal.antlr.PascalParser.SignedFactorContext;
import com.khubla.kpascal.antlr.PascalParser.SimpleExpressionContext;
import com.khubla.kpascal.antlr.PascalParser.SimpleStatementContext;
import com.khubla.kpascal.antlr.PascalParser.SimpleTypeContext;
import com.khubla.kpascal.antlr.PascalParser.StatementContext;
import com.khubla.kpascal.antlr.PascalParser.StatementsContext;
import com.khubla.kpascal.antlr.PascalParser.StringContext;
import com.khubla.kpascal.antlr.PascalParser.StringtypeContext;
import com.khubla.kpascal.antlr.PascalParser.StructuredStatementContext;
import com.khubla.kpascal.antlr.PascalParser.StructuredTypeContext;
import com.khubla.kpascal.antlr.PascalParser.SubrangeTypeContext;
import com.khubla.kpascal.antlr.PascalParser.TagContext;
import com.khubla.kpascal.antlr.PascalParser.TermContext;
import com.khubla.kpascal.antlr.PascalParser.TypeContext;
import com.khubla.kpascal.antlr.PascalParser.TypeDefinitionContext;
import com.khubla.kpascal.antlr.PascalParser.TypeDefinitionPartContext;
import com.khubla.kpascal.antlr.PascalParser.TypeIdentifierContext;
import com.khubla.kpascal.antlr.PascalParser.TypeListContext;
import com.khubla.kpascal.antlr.PascalParser.UnlabelledStatementContext;
import com.khubla.kpascal.antlr.PascalParser.UnpackedStructuredTypeContext;
import com.khubla.kpascal.antlr.PascalParser.UnsignedConstantContext;
import com.khubla.kpascal.antlr.PascalParser.UnsignedIntegerContext;
import com.khubla.kpascal.antlr.PascalParser.UnsignedNumberContext;
import com.khubla.kpascal.antlr.PascalParser.UnsignedRealContext;
import com.khubla.kpascal.antlr.PascalParser.UsesUnitsPartContext;
import com.khubla.kpascal.antlr.PascalParser.VariableContext;
import com.khubla.kpascal.antlr.PascalParser.VariableDeclarationContext;
import com.khubla.kpascal.antlr.PascalParser.VariableDeclarationPartContext;
import com.khubla.kpascal.antlr.PascalParser.VariantContext;
import com.khubla.kpascal.antlr.PascalParser.VariantPartContext;
import com.khubla.kpascal.antlr.PascalParser.WhileStatementContext;
import com.khubla.kpascal.antlr.PascalParser.WithStatementContext;

public class PascalInterpreter implements PascalListener {
   /**
    * context
    */
   private final Context context = new Context();
   private final InputStream pascalInputStream;
   private final InputStream stdIn;
   private final OutputStream stdOut;

   /**
    * ctor
    */
   public PascalInterpreter(InputStream pascalInputStream, InputStream stdIn, OutputStream stdOut) {
      this.pascalInputStream = pascalInputStream;
      this.stdIn = stdIn;
      this.stdOut = stdOut;
   }

   @Override
   public void enterActualParameter(ActualParameterContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterArrayType(ArrayTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterAssignmentStatement(AssignmentStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterBaseType(BaseTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterBlock(BlockContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterCaseListElement(CaseListElementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterCaseStatement(CaseStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterComponentType(ComponentTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterCompoundStatement(CompoundStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterConditionalStatement(ConditionalStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterConstant(ConstantContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterConstantChr(ConstantChrContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterConstantDefinition(ConstantDefinitionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterConstantDefinitionPart(ConstantDefinitionPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterConstList(ConstListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterElement(ElementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterElementList(ElementListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterEmpty(EmptyContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterEmptyStatement(EmptyStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterEveryRule(ParserRuleContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterExpression(ExpressionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterFactor(FactorContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterFieldList(FieldListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterFileType(FileTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterFinalValue(FinalValueContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterFixedPart(FixedPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterForList(ForListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterFormalParameterList(FormalParameterListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterFormalParameterSection(FormalParameterSectionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterForStatement(ForStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterFunctionDeclaration(FunctionDeclarationContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterFunctionDesignator(FunctionDesignatorContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterFunctionType(FunctionTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterGotoStatement(GotoStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterIdentifier(IdentifierContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterIdentifierList(IdentifierListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterIfStatement(IfStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterIndexType(IndexTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterInitialValue(InitialValueContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterLabel(LabelContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterLabelDeclarationPart(LabelDeclarationPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterParameterGroup(ParameterGroupContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterParameterList(ParameterListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterPointerType(PointerTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterProcedureAndFunctionDeclarationPart(ProcedureAndFunctionDeclarationPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterProcedureDeclaration(ProcedureDeclarationContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterProcedureOrFunctionDeclaration(ProcedureOrFunctionDeclarationContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterProcedureStatement(ProcedureStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterProcedureType(ProcedureTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterProgram(ProgramContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterProgramHeading(ProgramHeadingContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterRecordSection(RecordSectionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterRecordType(RecordTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterRecordVariableList(RecordVariableListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterRepeatStatement(RepeatStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterRepetetiveStatement(RepetetiveStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterResultType(ResultTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterScalarType(ScalarTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterSet(SetContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterSetType(SetTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterSign(SignContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterSignedFactor(SignedFactorContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterSimpleExpression(SimpleExpressionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterSimpleStatement(SimpleStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterSimpleType(SimpleTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterStatement(StatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterStatements(StatementsContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterString(StringContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterStringtype(StringtypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterStructuredStatement(StructuredStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterStructuredType(StructuredTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterSubrangeType(SubrangeTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterTag(TagContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterTerm(TermContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterType(TypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterTypeDefinition(TypeDefinitionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterTypeDefinitionPart(TypeDefinitionPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterTypeIdentifier(TypeIdentifierContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterTypeList(TypeListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterUnlabelledStatement(UnlabelledStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterUnpackedStructuredType(UnpackedStructuredTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterUnsignedConstant(UnsignedConstantContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterUnsignedInteger(UnsignedIntegerContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterUnsignedNumber(UnsignedNumberContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterUnsignedReal(UnsignedRealContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterUsesUnitsPart(UsesUnitsPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterVariable(VariableContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterVariableDeclaration(VariableDeclarationContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterVariableDeclarationPart(VariableDeclarationPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterVariant(VariantContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterVariantPart(VariantPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterWhileStatement(WhileStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void enterWithStatement(WithStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitActualParameter(ActualParameterContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitArrayType(ArrayTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitAssignmentStatement(AssignmentStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitBaseType(BaseTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitBlock(BlockContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitCaseListElement(CaseListElementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitCaseStatement(CaseStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitComponentType(ComponentTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitCompoundStatement(CompoundStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitConditionalStatement(ConditionalStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitConstant(ConstantContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitConstantChr(ConstantChrContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitConstantDefinition(ConstantDefinitionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitConstantDefinitionPart(ConstantDefinitionPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitConstList(ConstListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitElement(ElementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitElementList(ElementListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitEmpty(EmptyContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitEmptyStatement(EmptyStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitEveryRule(ParserRuleContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitExpression(ExpressionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitFactor(FactorContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitFieldList(FieldListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitFileType(FileTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitFinalValue(FinalValueContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitFixedPart(FixedPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitForList(ForListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitFormalParameterList(FormalParameterListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitFormalParameterSection(FormalParameterSectionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitForStatement(ForStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitFunctionDeclaration(FunctionDeclarationContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitFunctionDesignator(FunctionDesignatorContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitFunctionType(FunctionTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitGotoStatement(GotoStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitIdentifier(IdentifierContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitIdentifierList(IdentifierListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitIfStatement(IfStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitIndexType(IndexTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitInitialValue(InitialValueContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitLabel(LabelContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitLabelDeclarationPart(LabelDeclarationPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitParameterGroup(ParameterGroupContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitParameterList(ParameterListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitPointerType(PointerTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitProcedureAndFunctionDeclarationPart(ProcedureAndFunctionDeclarationPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitProcedureDeclaration(ProcedureDeclarationContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitProcedureOrFunctionDeclaration(ProcedureOrFunctionDeclarationContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitProcedureStatement(ProcedureStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitProcedureType(ProcedureTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitProgram(ProgramContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitProgramHeading(ProgramHeadingContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitRecordSection(RecordSectionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitRecordType(RecordTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitRecordVariableList(RecordVariableListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitRepeatStatement(RepeatStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitRepetetiveStatement(RepetetiveStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitResultType(ResultTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitScalarType(ScalarTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitSet(SetContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitSetType(SetTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitSign(SignContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitSignedFactor(SignedFactorContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitSimpleExpression(SimpleExpressionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitSimpleStatement(SimpleStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitSimpleType(SimpleTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitStatement(StatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitStatements(StatementsContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitString(StringContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitStringtype(StringtypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitStructuredStatement(StructuredStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitStructuredType(StructuredTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitSubrangeType(SubrangeTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitTag(TagContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitTerm(TermContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitType(TypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitTypeDefinition(TypeDefinitionContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitTypeDefinitionPart(TypeDefinitionPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitTypeIdentifier(TypeIdentifierContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitTypeList(TypeListContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitUnlabelledStatement(UnlabelledStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitUnpackedStructuredType(UnpackedStructuredTypeContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitUnsignedConstant(UnsignedConstantContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitUnsignedInteger(UnsignedIntegerContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitUnsignedNumber(UnsignedNumberContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitUnsignedReal(UnsignedRealContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitUsesUnitsPart(UsesUnitsPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitVariable(VariableContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitVariableDeclaration(VariableDeclarationContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitVariableDeclarationPart(VariableDeclarationPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitVariant(VariantContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitVariantPart(VariantPartContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitWhileStatement(WhileStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   @Override
   public void exitWithStatement(WithStatementContext ctx) {
      // TODO Auto-generated method stub
   }

   public Context getContext() {
      return context;
   }

   public InputStream getPascalInputStream() {
      return pascalInputStream;
   }

   public InputStream getStdIn() {
      return stdIn;
   }

   public OutputStream getStdOut() {
      return stdOut;
   }

   /**
    * parse an input file
    */
   private ProgramContext parse(InputStream inputStream) throws Exception {
      try {
         if (null != inputStream) {
            final Reader reader = new InputStreamReader(inputStream, "UTF-8");
            final PascalLexer lexer = new PascalLexer(new ANTLRInputStream(reader));
            final CommonTokenStream tokens = new CommonTokenStream(lexer);
            final PascalParser parser = new PascalParser(tokens);
            parser.setBuildParseTree(true);
            return parser.program();
         } else {
            throw new IllegalArgumentException();
         }
      } catch (final Exception e) {
         throw new Exception("Exception reading and parsing file", e);
      }
   }

   public void run() throws Exception {
      try {
         parse(pascalInputStream);
      } catch (final Exception e) {
         throw new Exception("Exception in run", e);
      }
   }

   @Override
   public void visitErrorNode(ErrorNode node) {
      // TODO Auto-generated method stub
   }

   @Override
   public void visitTerminal(TerminalNode node) {
      // TODO Auto-generated method stub
   }
}
