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

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;

import com.khubla.kpascal.antlr.PascalLexer;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.antlr.PascalParser.ProgramContext;

public class PascalInterpreter {
   /**
    * input stream
    */
   private final InputStream pascalInputStream;
   /**
    * root block
    */
   private Block rootBlock = null;

   /**
    * ctor
    */
   public PascalInterpreter(InputStream pascalInputStream, InputStream stdIn, OutputStream stdOut) {
      /*
       * pascal input stream
       */
      this.pascalInputStream = pascalInputStream;
   }

   public InputStream getPascalInputStream() {
      return pascalInputStream;
   }

   public Block getRootBlock() {
      return rootBlock;
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
          * find root block
          */
         PascalParser.BlockContext rootBlockContext = null;
         for (int i = 0; i < programContext.getChildCount(); i++) {
            final RuleContext ruleContext = (RuleContext) programContext.getChild(i);
            if (ruleContext instanceof PascalParser.BlockContext) {
               rootBlockContext = (PascalParser.BlockContext) ruleContext;
               break;
            }
         }
         if (null != rootBlockContext) {
            rootBlock = new Block(rootBlockContext);
            rootBlock.run();
         }
      } catch (final Exception e) {
         throw new Exception("Exception in run", e);
      }
   }
}
