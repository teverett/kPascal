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

import com.khubla.kpascal.listener.BlockListener;
import com.khubla.kpascal.listener.ParameterGroupListener.ParameterGroup;
import com.khubla.kpascal.listener.ParameterGroupListener.ParameterType;
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser.BlockContext;

public class FunctionOrProcedureDefinition {
   private final String name;
   private final List<ParameterGroup> parameterGroups;
   private final String resultType;
   private final BlockContext blockContext;

   /**
    * procedure
    */
   public FunctionOrProcedureDefinition(String name, List<ParameterGroup> parameterGroups, BlockContext blockContext) {
      this.name = name;
      this.parameterGroups = parameterGroups;
      resultType = null;
      this.blockContext = blockContext;
   }

   /**
    * function
    */
   public FunctionOrProcedureDefinition(String name, List<ParameterGroup> parameterGroups, BlockContext blockContext, String resultType) {
      this.name = name;
      this.parameterGroups = parameterGroups;
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
      for (final ParameterGroup parameterGroup : parameterGroups) {
         for (final String identifier : parameterGroup.getIdentifiers()) {
            if (parameterGroup.getParameterType() == ParameterType.var) {
               /*
                * put the existing variable into scope (shallow copy)
                */
               stackFrame.declareVariable(identifier, args.get(i++));
            } else if (parameterGroup.getParameterType() == ParameterType.function) {
               throw new RuntimeException("not implemented");
            } else if (parameterGroup.getParameterType() == ParameterType.procedure) {
               throw new RuntimeException("not implemented");
            } else {
               /*
                * put new variable into scope (deep copy)
                */
               throw new RuntimeException("not implemented");
            }
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

   public List<ParameterGroup> getParameterGroups() {
      return parameterGroups;
   }

   public String getResultType() {
      return resultType;
   }
}
