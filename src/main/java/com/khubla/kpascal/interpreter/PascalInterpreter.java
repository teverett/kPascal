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

import com.khubla.kpascal.antlr.PascalLexer;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.antlr.PascalParser.ProgramContext;
import com.khubla.kpascal.interpreter.visitor.ProgramVisitor;
import com.khubla.kpascal.interpreter.visitor.TypeVisitor;
import com.khubla.kpascal.interpreter.visitor.VariableVisitor;

public class PascalInterpreter {
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
   private final Stack<Scope> scopeStack = new Stack<Scope>();
   /**
    * interpreter stack
    */
   private final Stack<String> interpreterStack = new Stack<String>();

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
      scopeStack.push(new Scope());
   }

   /**
    * the current scope is the scope on the top of the stack
    */
   public Scope getCurrentScope() {
      return scopeStack.get(0);
   }

   public Stack<String> getInterpreterStack() {
      return interpreterStack;
   }

   public InputStream getPascalInputStream() {
      return pascalInputStream;
   }

   public Hashtable<String, Procedure> getProcedures() {
      return procedures;
   }

   public String getProgramName() {
      return programName;
   }

   public Stack<Scope> getScopeStack() {
      return scopeStack;
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
            final PascalParser parser = new PascalParser(new CommonTokenStream(lexer));
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
         /*
          * parse
          */
         final ProgramContext programContext = parse(pascalInputStream);
         /*
          * types
          */
         final TypeVisitor typeVisitor = new TypeVisitor(getCurrentScope());
         typeVisitor.visit(programContext);
         /*
          * variables
          */
         final VariableVisitor variableVisitor = new VariableVisitor(getCurrentScope());
         variableVisitor.visit(programContext);
         /*
          * program
          */
         final ProgramVisitor programVisitor = new ProgramVisitor(getCurrentScope());
         programVisitor.visit(programContext);
      } catch (final Exception e) {
         throw new Exception("Exception in run", e);
      }
   }
}
