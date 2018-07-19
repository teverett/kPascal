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
package com.khubla.kpascal;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;

import com.khubla.kpascal.listener.ProgramListener;
import com.khubla.pascal.pascalLexer;
import com.khubla.pascal.pascalParser;
import com.khubla.pascal.pascalParser.ProgramContext;

public class Interpreter {
   /*
    * execution context
    */
   final ExecutionContext executionContext;;

   public Interpreter() {
      executionContext = new ExecutionContext();
   }

   public Interpreter(InputStream consoleInput, PrintStream consoleOut) {
      executionContext = new ExecutionContext(consoleInput, consoleOut);
   }

   /**
    * parse MUMPS program from inputstream
    */
   private ProgramContext parse(InputStream inputStream) throws IOException {
      final Lexer lexer = new pascalLexer(CharStreams.fromStream(inputStream));
      final TokenStream tokenStream = new CommonTokenStream(lexer);
      final pascalParser parser = new pascalParser(tokenStream);
      return parser.program();
   }

   public void run(InputStream inputStream) throws Exception {
      try {
         /*
          * parse program
          */
         final ProgramContext programContext = parse(inputStream);
         if (null != programContext) {
            /*
             * enter program listener
             */
            final ProgramListener programListener = new ProgramListener(executionContext);
            programContext.enterRule(programListener);
         }
      } catch (final Exception e) {
         throw new Exception("Exception in run", e);
      }
   }
}
