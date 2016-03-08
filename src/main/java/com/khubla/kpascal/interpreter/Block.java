package com.khubla.kpascal.interpreter;

import org.antlr.v4.runtime.RuleContext;

import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.antlr.PascalParser.BlockContext;
import com.khubla.kpascal.interpreter.visitor.CompoundStatementVisitor;
import com.khubla.kpascal.interpreter.visitor.ConstantVisitor;
import com.khubla.kpascal.interpreter.visitor.ProcedureVisitor;
import com.khubla.kpascal.interpreter.visitor.TypeVisitor;
import com.khubla.kpascal.interpreter.visitor.VariableVisitor;

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
public class Block {
   /**
    * block context
    */
   private final BlockContext blockContext;
   /**
    * context
    */
   private final Context context;

   /**
    * ctor
    */
   public Block(PascalParser.BlockContext blockContext) {
      this.blockContext = blockContext;
      /*
       * context
       */
      context = new Context();
      /*
       * push the program context
       */
      context.getScopeStack().push(new Scope());
      /*
       * constants
       */
      final ConstantVisitor constantVisitor = new ConstantVisitor(context);
      constantVisitor.visit(blockContext);
      /*
       * types
       */
      final TypeVisitor typeVisitor = new TypeVisitor(context);
      typeVisitor.visit(blockContext);
      getContext().getCurrentScope().getTypes().resolveComponentTypes();
      /*
       * variables
       */
      final VariableVisitor variableVisitor = new VariableVisitor(context);
      variableVisitor.visit(blockContext);
      /*
       * procedures
       */
      final ProcedureVisitor procedureVisitor = new ProcedureVisitor(context);
      procedureVisitor.visit(blockContext);
   }

   public BlockContext getBlockContext() {
      return blockContext;
   }

   public Context getContext() {
      return context;
   }

   /**
    * run the compound statement
    */
   public void run() {
      PascalParser.CompoundStatementContext compoundStatementContext = null;
      for (int i = 0; i < blockContext.getChildCount(); i++) {
         final RuleContext ruleContext = (RuleContext) blockContext.getChild(i);
         if (ruleContext instanceof PascalParser.CompoundStatementContext) {
            compoundStatementContext = (PascalParser.CompoundStatementContext) ruleContext;
            break;
         }
      }
      if (null != compoundStatementContext) {
         final CompoundStatementVisitor compoundStatementVisitor = new CompoundStatementVisitor(context);
         compoundStatementVisitor.visit(compoundStatementContext);
      }
   }
}
