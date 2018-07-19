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
import java.util.Stack;

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

   public StackFrame popStackframe() {
      return stack.pop();
   }

   public StackFrame pushStackframe() {
      final StackFrame stackFrame = new StackFrame();
      stack.push(stackFrame);
      return stackFrame;
   }

   public Value resolveVariable(String name) {
      /*
       * walk the stack, top to bottom
       */
      for (int i = 0; i < stack.size(); i++) {
         final StackFrame stackFrame = stack.get(i);
         if (stackFrame.getVariables().containsKey(name)) {
            return stackFrame.getVariables().get(name);
         }
      }
      return null;
   }
}
