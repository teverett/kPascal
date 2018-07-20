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

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Stack;

import com.khubla.kpascal.runtime.function.RuntimeFunction;
import com.khubla.kpascal.runtime.function.RuntimeFunctionFactory;
import com.khubla.kpascal.type.Type;
import com.khubla.kpascal.value.Value;

/**
 * the execution context.
 *
 * @author tom
 */
public class ExecutionContext {
   /**
    * input
    */
   private final InputStream consoleInput;
   /**
    * output
    */
   private final PrintStream consoleOut;
   /**
    * stack
    */
   private final Stack<StackFrame> stack = new Stack<StackFrame>();
   /**
    * function factory
    */
   private final RuntimeFunctionFactory runtimeFunctionFactory = new RuntimeFunctionFactory();

   public ExecutionContext() {
      consoleOut = System.out;
      consoleInput = System.in;
   }

   public ExecutionContext(InputStream consoleInput, PrintStream consoleOut) {
      this.consoleOut = consoleOut;
      this.consoleInput = consoleInput;
   }

   public InputStream getConsoleInput() {
      return consoleInput;
   }

   public PrintStream getConsoleOut() {
      return consoleOut;
   }

   public StackFrame getCurrentStackframe() {
      return stack.peek();
   }

   public Stack<StackFrame> getStack() {
      return stack;
   }

   public Value invokeFunction(String name, List<Value> values) {
      final RuntimeFunction runtimeFunction = runtimeFunctionFactory.getRuntimeFunction(name);
      if (null != runtimeFunction) {
         return runtimeFunction.execute(this, values);
      } else {
         final FunctionOrProcedureDefinition functionOrProcedureDefinition = resolveFunctionOrProcedure(name);
         if (null != functionOrProcedureDefinition) {
            // execute it
            return null;
         } else {
            throw new RuntimeException("Unknown procedure '" + name + "'");
         }
      }
   }

   public StackFrame popStackframe() {
      return stack.pop();
   }

   public StackFrame pushStackframe() {
      final StackFrame stackFrame = new StackFrame();
      stack.push(stackFrame);
      return stackFrame;
   }

   /**
    * walk the stack, top to bottom trying to find the type
    */
   private FunctionOrProcedureDefinition resolveFunctionOrProcedure(String name) {
      for (int i = 0; i < stack.size(); i++) {
         final StackFrame stackFrame = stack.get(i);
         final FunctionOrProcedureDefinition functionOrProcedureDefinition = stackFrame.getFunctionOrProcedureDefinition(name);
         if (null != functionOrProcedureDefinition) {
            return functionOrProcedureDefinition;
         }
      }
      throw new RuntimeException("Unable to resolve '" + name + "'");
   }

   /**
    * walk the stack, top to bottom trying to find the type
    */
   public Type resolveType(String name) {
      for (int i = 0; i < stack.size(); i++) {
         final StackFrame stackFrame = stack.get(i);
         final TypeDefinition typeDefinition = stackFrame.getType(name);
         if (null != typeDefinition) {
            return typeDefinition.getType();
         }
      }
      throw new RuntimeException("Unable to resolve '" + name + "'");
   }

   /**
    * walk the stack, top to bottom trying to find the variable
    */
   public Value resolveVariable(String name) {
      for (int i = 0; i < stack.size(); i++) {
         final StackFrame stackFrame = stack.get(i);
         Value value = stackFrame.getVariable(name);
         if (null == value) {
            value = stackFrame.getConstant(name);
         }
         if (null != value) {
            return value;
         }
      }
      throw new RuntimeException("Unable to resolve '" + name + "'");
   }
}
