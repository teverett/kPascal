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
import java.util.Stack;

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
    * program name
    */
   private String programName;
   /**
    * context
    */
   private final InputStream pascalInputStream;
   private final InputStream stdIn;
   private final OutputStream stdOut;
   /**
    * all procedures by name
    */
   private final Hashtable<String, Procedure> procedures = new Hashtable<String, Procedure>();
   /**
    * stack of execution contexts
    */
   private final Stack<Context> contextStack = new Stack<Context>();
   /**
    * interpreter stack
    */
   private final Stack<String> interpreterStack = new Stack<String>();

   public Stack<String> getInterpreterStack() {
      return interpreterStack;
   }

   /**
    * ctor
    */
   public PascalInterpreter(InputStream pascalInputStream, InputStream stdIn, OutputStream stdOut) {
      this.pascalInputStream = pascalInputStream;
      this.stdIn = stdIn;
      this.stdOut = stdOut;
      /**
       * push the program context
       */
      contextStack.push(new Context());
   }

   public Stack<Context> getContextStack() {
      return contextStack;
   }

   /**
    * the current context is the context on the top of the stack
    */
   public Context getCurrentContext() {
      return contextStack.get(0);
   }

   public InputStream getPascalInputStream() {
      return pascalInputStream;
   }

   public Hashtable<String, Procedure> getProcedures() {
      return procedures;
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
      new Context(getCurrentContext());
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
      v = new VariableInstance(name, type, VariableInstance.VariableDeclarationType.constant, value);
      getCurrentContext().getVariables().put(name, v);
      return visitChildren(ctx);
   }

   @Override
   public ProgramContext visitProcedureDeclaration(PascalParser.ProcedureDeclarationContext ctx) {
      final String name = ctx.getChild(1).getText();
      procedures.put(name, new Procedure(ctx));
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
      ctx.getChild(1);
      return visitChildren(ctx);
   }

   @Override
   public ProgramContext visitVariableDeclaration(PascalParser.VariableDeclarationContext ctx) {
      final String instanceName = ctx.getChild(0).getText();
      final String typeName = ctx.getChild(2).getText();
      final Type type = getCurrentContext().getTypes().find(typeName);
      if (null != type) {
         final VariableInstance v = new VariableInstance(instanceName, type, VariableInstance.VariableDeclarationType.variable, null);
         getCurrentContext().getVariables().put(instanceName, v);
      } else {
         System.out.println("Unknown type '" + typeName + "'");
      }
      return visitChildren(ctx);
   }

   @Override
   public ProgramContext visitIdentifier(PascalParser.IdentifierContext ctx) {
      String identifier = ctx.getText();
      System.out.println("Identifier: " + identifier);
      this.interpreterStack.push(identifier);
      return visitChildren(ctx);
   }

   @Override
   public ProgramContext visitProgramHeading(PascalParser.ProgramHeadingContext ctx) {
      int arguments = (ctx.getChild(3).getChildCount() + 1) / 2;
      ProgramContext returnCtx = visitChildren(ctx);
      for (int i = 0; i < arguments; i++) {
         this.interpreterStack.pop();
      }
      programName = this.interpreterStack.pop();
      return returnCtx;
   }

   public String getProgramName() {
      return programName;
   }

   @Override
   public ProgramContext visitUnsignedInteger(PascalParser.UnsignedIntegerContext ctx) {
      String unsignedInteger = ctx.getText();
      System.out.println("unsignedInteger: " + unsignedInteger);
      this.interpreterStack.push(unsignedInteger);
      return visitChildren(ctx);
   }

   @Override
   public ProgramContext visitUnsignedReal(PascalParser.UnsignedRealContext ctx) {
      String unsignedReal = ctx.getText();
      System.out.println("unsignedReal: " + unsignedReal);
      this.interpreterStack.push(unsignedReal);
      return visitChildren(ctx);
   }
}
