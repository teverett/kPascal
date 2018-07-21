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

import java.util.List;

import com.khubla.kpascal.exception.NotImplementedException;
import com.khubla.kpascal.listener.BlockListener;
import com.khubla.kpascal.listener.ParameterGroupListener.Parameter;
import com.khubla.kpascal.listener.ParameterGroupListener.ParameterType;
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser.BlockContext;

public class FunctionOrProcedureDefinition {
   private final String name;
   private final List<Parameter> parameters;
   private final String resultType;
   private final BlockContext blockContext;

   /**
    * procedure
    */
   public FunctionOrProcedureDefinition(String name, List<Parameter> parameters, BlockContext blockContext) {
      this.name = name;
      this.parameters = parameters;
      resultType = null;
      this.blockContext = blockContext;
   }

   /**
    * function
    */
   public FunctionOrProcedureDefinition(String name, List<Parameter> parameters, BlockContext blockContext, String resultType) {
      this.name = name;
      this.parameters = parameters;
      this.resultType = resultType;
      this.blockContext = blockContext;
   }

   public Value execute(ExecutionContext executionContext, List<Value> args) {
      /*
       * new stack frame
       */
      final StackFrame stackFrame = executionContext.pushStackframe();
      /*
       * put the variables into scope
       */
      int i = 0;
      for (final Parameter parameter : parameters) {
         if (parameter.getParameterType() == ParameterType.var) {
            /*
             * put the existing variable into scope (shallow copy)
             */
            stackFrame.declareVariable(parameter.getName(), args.get(i++));
         } else if (parameter.getParameterType() == ParameterType.function) {
            throw new NotImplementedException();
         } else if (parameter.getParameterType() == ParameterType.procedure) {
            throw new NotImplementedException();
         } else {
            /*
             * put new variable into scope (deep copy)
             */
            throw new NotImplementedException();
         }
      }
      /*
       * run the block
       */
      final BlockListener blockListener = new BlockListener(executionContext);
      blockListener.enterBlock(blockContext);
      /*
       * done
       */
      executionContext.popStackframe();
      return null;
   }

   public BlockContext getBlockContext() {
      return blockContext;
   }

   public String getName() {
      return name;
   }

   public List<Parameter> getParameters() {
      return parameters;
   }

   public String getResultType() {
      return resultType;
   }
}
