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
import java.io.OutputStream;
import java.util.List;
import java.util.Stack;

import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.listener.ExpressionListener;
import com.khubla.kpascal.runtime.function.RuntimeFunction;
import com.khubla.kpascal.runtime.function.RuntimeFunctionFactory;
import com.khubla.kpascal.type.BooleanType;
import com.khubla.kpascal.type.CharacterType;
import com.khubla.kpascal.type.IntegerType;
import com.khubla.kpascal.type.RealType;
import com.khubla.kpascal.type.StringType;
import com.khubla.kpascal.type.Type;
import com.khubla.kpascal.value.BooleanValue;
import com.khubla.kpascal.value.Value;
import com.khubla.pascal.pascalParser.ExpressionContext;

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
   private final OutputStream consoleOut;
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

   public ExecutionContext(InputStream consoleInput, OutputStream consoleOut) {
      this.consoleOut = consoleOut;
      this.consoleInput = consoleInput;
   }

   public InputStream getConsoleInput() {
      return consoleInput;
   }

   public OutputStream getConsoleOut() {
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
            return functionOrProcedureDefinition.execute(this, values);
         } else {
            throw new InterpreterException("Unknown procedure '" + name + "'");
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

   private Type resolveBuiltinType(String name) {
      if (name.toLowerCase().compareTo("integer") == 0) {
         return new IntegerType();
      } else if (name.toLowerCase().compareTo("real") == 0) {
         return new RealType();
      } else if (name.toLowerCase().compareTo("string") == 0) {
         return new StringType();
      } else if (name.toLowerCase().compareTo("boolean") == 0) {
         return new BooleanType();
      } else if (name.toLowerCase().compareTo("char") == 0) {
         return new CharacterType();
      }
      return null;
   }

   /**
    * walk the stack, top to bottom trying to find the function or procedure
    */
   private FunctionOrProcedureDefinition resolveFunctionOrProcedure(String name) {
      for (int i = 0; i < stack.size(); i++) {
         final StackFrame stackFrame = stack.get(i);
         final FunctionOrProcedureDefinition functionOrProcedureDefinition = stackFrame.getFunctionOrProcedureDefinition(name);
         if (null != functionOrProcedureDefinition) {
            return functionOrProcedureDefinition;
         }
      }
      return null;
   }

   /**
    * walk the stack, top to bottom trying to find the type
    */
   public Type resolveType(String name) {
      final Type t = resolveBuiltinType(name);
      if (null != t) {
         return t;
      } else {
         for (int i = 0; i < stack.size(); i++) {
            final StackFrame stackFrame = stack.get(i);
            final TypeDefinition typeDefinition = stackFrame.getType(name);
            if (null != typeDefinition) {
               return typeDefinition.getType();
            }
         }
      }
      throw new InterpreterException("Unable to resolve '" + name + "'");
   }

   /**
    * walk the stack, top to bottom trying to find the variable or constant
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
      /*
       * it could be a function which takes no parameters, such as "readkey".
       */
      final RuntimeFunction runtimeFunction = runtimeFunctionFactory.getRuntimeFunction(name);
      if (null != runtimeFunction) {
         /*
          * no parameters
          */
         return runtimeFunction.execute(this, null);
      } else {
         final FunctionOrProcedureDefinition functionOrProcedureDefinition = resolveFunctionOrProcedure(name);
         if (null != functionOrProcedureDefinition) {
            /*
             * no parameters
             */
            return functionOrProcedureDefinition.execute(this, null);
         }
      }
      throw new InterpreterException("Unable to resolve '" + name + "'");
   }

   /**
    * test an expression. used in while loop, etc
    */
   public boolean testExpression(ExpressionContext expressionContext) {
      final ExpressionListener expressionListener = new ExpressionListener(this);
      expressionListener.enterExpression(expressionContext);
      final Value cond = expressionListener.getValue();
      if (cond instanceof BooleanValue) {
         return ((BooleanValue) cond).isValue();
      } else {
         throw new InterpreterException("Expected Boolean");
      }
   }
}
