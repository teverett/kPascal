package com.khubla.kpascal.interpreter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.kpascal.antlr.PascalParser.StatementContext;
import com.khubla.kpascal.interpreter.visitor.StatementVisitor;

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
public class Statement {
   /**
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   /**
    * statement context
    */
   private final StatementContext statementContext;
   /**
    * context
    */
   private final Context context;

   /**
    * ctor
    */
   public Statement(StatementContext statementContext, Context context) {
      this.statementContext = statementContext;
      this.context = context;
   }

   /**
    * run the compound statement in the block
    */
   public void run() {
      logger.info("Running statement: '" + statementContext.getText() + "'");
      final StatementVisitor statementVisitor = new StatementVisitor(context);
      statementVisitor.visit(statementContext);
   }
}
