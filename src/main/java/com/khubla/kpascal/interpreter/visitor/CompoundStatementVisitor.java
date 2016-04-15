package com.khubla.kpascal.interpreter.visitor;

import com.khubla.kpascal.antlr.PascalBaseVisitor;
import com.khubla.kpascal.antlr.PascalParser;
import com.khubla.kpascal.interpreter.Context;
import com.khubla.kpascal.interpreter.Statement;

public class CompoundStatementVisitor extends PascalBaseVisitor<Void> {
   /**
    * the context
    */
   private final Context context;

   /**
    * ctor
    */
   public CompoundStatementVisitor(Context context) {
      this.context = context;
   }

   @Override
   public Void visitStatement(PascalParser.StatementContext ctx) {
      final Statement statement = new Statement(ctx, context);
      statement.run();
      return visitChildren(ctx);
   }
}
