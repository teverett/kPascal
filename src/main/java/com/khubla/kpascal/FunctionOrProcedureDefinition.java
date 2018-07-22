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
import com.khubla.kpascal.type.Type;
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser.BlockContext;

public class FunctionOrProcedureDefinition {
   private final String name;
   private final List<Parameter> parameters;
   private final Type resultType;
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
   public FunctionOrProcedureDefinition(String name, List<Parameter> parameters, BlockContext blockContext, Type resultType) {
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
            stackFrame.declareVariable(parameter.getName(), args.get(i++).deepCopy());
         }
      }
      /*
       * if there is a return type, then its a function, and we need to put a variable into scope, with the name of the function and type of the function return type
       */
      if (isFunction()) {
         stackFrame.declareVariable(getName(), resultType.createValue());
      }
      /*
       * run the block
       */
      final BlockListener blockListener = new BlockListener(executionContext);
      blockListener.enterBlock(blockContext);
      /*
       * get the return value
       */
      Value ret = null;
      if (isFunction()) {
         ret = executionContext.resolveVariable(getName());
      }
      /*
       * done
       */
      executionContext.popStackframe();
      /*
       * return result of function invocation or null if it's a procedure
       */
      return ret;
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

   public Type getResultType() {
      return resultType;
   }

   public boolean isFunction() {
      return null != getResultType();
   }
}
