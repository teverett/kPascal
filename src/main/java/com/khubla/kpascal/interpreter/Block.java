package com.khubla.kpascal.interpreter;

import java.util.Enumeration;
import java.util.Set;

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
import org.antlr.v4.runtime.RuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.antlr.PascalParser.BlockContext;
import com.khubla.kpascal.interpreter.visitor.CompoundStatementVisitor;
import com.khubla.kpascal.interpreter.visitor.ConstantVisitor;
import com.khubla.kpascal.interpreter.visitor.ProcedureVisitor;
import com.khubla.kpascal.interpreter.visitor.TypeVisitor;
import com.khubla.kpascal.interpreter.visitor.VariableVisitor;
import com.khubla.kpascal.type.Type;

public class Block {
   /**
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   /**
    * block context
    */
   private final BlockContext blockContext;
   /**
    * context
    */
   private Context context;

   /**
    * ctor
    */
   public Block(PascalParser.BlockContext blockContext) {
      /*
       * set the blockContext
       */
      this.blockContext = blockContext;
      /*
       * context
       */
      context = new Context();
      /*
       * push the program context
       */
      // context.getScopeStack().push(new Scope());
   }

   public BlockContext getBlockContext() {
      return blockContext;
   }

   public Context getContext() {
      return context;
   }

   private void reportBlock() {
      /**
       * constants
       */
      if (context.getConstants().size() > 0) {
         logger.info("Constants");
         final Enumeration<String> e = context.getConstants().keys();
         while (e.hasMoreElements()) {
            logger.info(e.nextElement());
         }
      }
      /**
       * types
       */
      if (context.getProcedures().size() > 0) {
         logger.info("Procedures");
         final Enumeration<String> e = context.getProcedures().keys();
         while (e.hasMoreElements()) {
            logger.info(e.nextElement());
         }
      }
      /**
       * scope stack
       */
      if (context.getScopeStack().size() > 0) {
         for (int i = 0; i < context.getScopeStack().size(); i++) {
            final Scope scope = context.getScopeStack().get(i);
            /**
             * types
             */
            if (scope.getTypes().size() > 0) {
               logger.info("Scope '" + i + "' Types");
               final Set<String> typenames = scope.getTypes().keys();
               for (final String typename : typenames) {
                  final Type type = scope.getTypes().find(typename);
                  if (false == type.builtIn()) {
                     logger.info(typename);
                  }
               }
            }
            /**
             * variables
             */
            if (scope.getVariables().size() > 0) {
               logger.info("Scope '" + i + "' Variables");
               final Enumeration<String> e = scope.getVariables().keys();
               while (e.hasMoreElements()) {
                  logger.info(e.nextElement());
               }
            }
         }
      }
   }

   /**
    * run the compound statement in the block
    */
   public void run() {
      logger.info("Running block: '" + blockContext.getText() + "'");
      /*
       * add a scope
       */
      context.getScopeStack().pushScope();
      /*
       * constants. constants must be evaluated before types, b/c types can be defined using constants
       */
      final ConstantVisitor constantVisitor = new ConstantVisitor(context);
      constantVisitor.visit(blockContext);
      /*
       * types
       */
      final TypeVisitor typeVisitor = new TypeVisitor(context);
      typeVisitor.visit(blockContext);
      getContext().getScopeStack().getCurrentScope().getTypes().resolveComponentTypes();
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
      /*
       * report the block
       */
      reportBlock();
      /*
       * find the compound statement
       */
      PascalParser.CompoundStatementContext compoundStatementContext = null;
      for (int i = 0; i < blockContext.getChildCount(); i++) {
         final RuleContext ruleContext = (RuleContext) blockContext.getChild(i);
         if (ruleContext instanceof PascalParser.CompoundStatementContext) {
            compoundStatementContext = (PascalParser.CompoundStatementContext) ruleContext;
            break;
         }
      }
      if (null != compoundStatementContext) {
         logger.info("Running compound statment: '" + compoundStatementContext.getText() + "'");
         final CompoundStatementVisitor compoundStatementVisitor = new CompoundStatementVisitor(context);
         compoundStatementVisitor.visit(compoundStatementContext);
      }
      /*
       * we're done
       */
      context.getScopeStack().popScope();
   }

   public void setContext(Context context) {
      this.context = context;
   }
}
