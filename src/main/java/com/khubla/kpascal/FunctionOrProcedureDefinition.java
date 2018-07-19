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

import com.khubla.kpascal.listener.ParameterGroupListener.ParameterGroup;
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
