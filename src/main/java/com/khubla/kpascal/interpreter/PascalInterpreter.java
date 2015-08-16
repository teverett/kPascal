package com.khubla.kpascal.interpreter;

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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Hashtable;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalLexer;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.antlr.PascalParser.ProgramContext;
import com.khubla.kpascal.type.IntegerType;
import com.khubla.kpascal.type.RealType;
import com.khubla.kpascal.type.StringType;
import com.khubla.kpascal.type.Type;

public class PascalInterpreter extends PascalBaseVisitor<ProgramContext> {
   /**
    * context
    */
   private final Context context = new Context();
   private final InputStream pascalInputStream;
   private final InputStream stdIn;
   private final OutputStream stdOut;
   private final Hashtable<String, PascalParser.ProcedureDeclarationContext> procedures = new Hashtable<String, PascalParser.ProcedureDeclarationContext>();

   /**
    * ctor
    */
   public PascalInterpreter(InputStream pascalInputStream, InputStream stdIn, OutputStream stdOut) {
      this.pascalInputStream = pascalInputStream;
      this.stdIn = stdIn;
      this.stdOut = stdOut;
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
         final ProgramContext programContext = parse(pascalInputStream);
         programContext.accept(this);
      } catch (final Exception e) {
         throw new Exception("Exception in run", e);
      }
   }

   @Override
   public ProgramContext visitBlock(PascalParser.BlockContext ctx) {
      // code starts here
      return visitChildren(ctx);
   }

   @Override
   public ProgramContext visitConstantDefinition(PascalParser.ConstantDefinitionContext ctx) {
      final String name = ctx.getChild(0).getText();
      final String value = ctx.getChild(2).getText();
      final ParserRuleContext parserRuleContext = (ParserRuleContext) ctx.getChild(2).getChild(0).getChild(0);
      VariableInstance v = null;
      Type type = null;
      if (parserRuleContext instanceof PascalParser.UnsignedRealContext) {
         type = new RealType();
      } else if (parserRuleContext instanceof PascalParser.UnsignedIntegerContext) {
         type = new IntegerType();
      } else if (parserRuleContext instanceof PascalParser.StringContext) {
         type = new StringType();
      }
      v = new VariableInstance(name, type, VariableInstance.VariableDeclarationType.constant, 0, value);
      context.getVariables().put(name, v);
      return visitChildren(ctx);
   }

   @Override
   public ProgramContext visitProcedureDeclaration(PascalParser.ProcedureDeclarationContext ctx) {
      final String name = ctx.getChild(1).getText();
      procedures.put(name, ctx);
      return visitChildren(ctx);
   }

   @Override
   public ProgramContext visitProcedureStatement(PascalParser.ProcedureStatementContext ctx) {
      ctx.getChild(0).getText();
      return visitChildren(ctx);
   }

   @Override
   public ProgramContext visitTypeDefinition(PascalParser.TypeDefinitionContext ctx) {
      ctx.getChild(0).getText();
      return visitChildren(ctx);
   }

   @Override
   public ProgramContext visitVariableDeclaration(PascalParser.VariableDeclarationContext ctx) {
      ctx.getChild(0).getText();
      ctx.getChild(2).getText();
      return visitChildren(ctx);
   }
}
