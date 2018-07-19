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
import com.khubla.kpascal.listener.part.ConstantDefinitionPartListener;
import com.khubla.kpascal.listener.part.LabelDeclarationPartListener;
import com.khubla.kpascal.listener.part.ProcedureAndFunctionDeclarationPartListener;
import com.khubla.kpascal.listener.part.TypeDefinitionPartListener;
import com.khubla.kpascal.listener.part.UsesUnitsPartListener;
import com.khubla.kpascal.listener.part.VariableDeclarationPartListener;
import com.khubla.pascal.pascalParser;
import com.khubla.pascal.pascalParser.ConstantDefinitionPartContext;
import com.khubla.pascal.pascalParser.LabelDeclarationPartContext;
import com.khubla.pascal.pascalParser.ProcedureAndFunctionDeclarationPartContext;
import com.khubla.pascal.pascalParser.TypeDefinitionPartContext;
import com.khubla.pascal.pascalParser.UsesUnitsPartContext;
import com.khubla.pascal.pascalParser.VariableDeclarationPartContext;

public class BlockListener extends AbstractkPascalListener {
   public BlockListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterBlock(pascalParser.BlockContext ctx) {
      /*
       * new stack frame
       */
      getExecutionContext().pushStackframe();
      /*
       * uses
       */
      if (null != ctx.usesUnitsPart()) {
         for (final UsesUnitsPartContext usesUnitsPartContext : ctx.usesUnitsPart()) {
            final UsesUnitsPartListener usesUnitsPartListener = new UsesUnitsPartListener(getExecutionContext());
            usesUnitsPartListener.enterUsesUnitsPart(usesUnitsPartContext);
         }
      }
      /*
       * constants
       */
      if (null != ctx.constantDefinitionPart()) {
         for (final ConstantDefinitionPartContext constantDefinitionPartContext : ctx.constantDefinitionPart()) {
            final ConstantDefinitionPartListener constantDefinitionPartListener = new ConstantDefinitionPartListener(getExecutionContext());
            constantDefinitionPartListener.enterConstantDefinitionPart(constantDefinitionPartContext);
         }
      }
      /*
       * types
       */
      if (null != ctx.typeDefinitionPart()) {
         for (final TypeDefinitionPartContext typeDefinitionPartContext : ctx.typeDefinitionPart()) {
            final TypeDefinitionPartListener typeDefinitionPartListener = new TypeDefinitionPartListener(getExecutionContext());
            typeDefinitionPartListener.enterTypeDefinitionPart(typeDefinitionPartContext);
         }
      }
      /*
       * labels
       */
      if (null != ctx.labelDeclarationPart()) {
         for (final LabelDeclarationPartContext labelDeclarationPartContext : ctx.labelDeclarationPart()) {
            final LabelDeclarationPartListener labelDeclarationPartListener = new LabelDeclarationPartListener(getExecutionContext());
            labelDeclarationPartListener.enterLabelDeclarationPart(labelDeclarationPartContext);
         }
      }
      /*
       * funcs
       */
      if (null != ctx.procedureAndFunctionDeclarationPart()) {
         for (final ProcedureAndFunctionDeclarationPartContext procedureAndFunctionDeclarationPartContext : ctx.procedureAndFunctionDeclarationPart()) {
            final ProcedureAndFunctionDeclarationPartListener procedureAndFunctionDeclarationPartListener = new ProcedureAndFunctionDeclarationPartListener(getExecutionContext());
            procedureAndFunctionDeclarationPartListener.enterProcedureAndFunctionDeclarationPart(procedureAndFunctionDeclarationPartContext);
         }
      }
      /*
       * vars?
       */
      if (null != ctx.variableDeclarationPart()) {
         for (final VariableDeclarationPartContext variableDeclarationPartContext : ctx.variableDeclarationPart()) {
            final VariableDeclarationPartListener variableDeclarationPartListener = new VariableDeclarationPartListener(getExecutionContext());
            variableDeclarationPartListener.enterVariableDeclarationPart(variableDeclarationPartContext);
         }
      }
      /*
       * stmt
       */
      if (null != ctx.compoundStatement()) {
         final CompoundStatementListener compoundStatementListener = new CompoundStatementListener((getExecutionContext()));
         compoundStatementListener.enterCompoundStatement(ctx.compoundStatement());
      }
   }

   @Override
   public void exitBlock(pascalParser.BlockContext ctx) {
      /*
       * done
       */
      getExecutionContext().popStackframe();
   }
}
