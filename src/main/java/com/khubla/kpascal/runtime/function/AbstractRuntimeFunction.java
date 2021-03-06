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
package com.khubla.kpascal.runtime.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.khubla.kpascal.ExecutionContext;

public abstract class AbstractRuntimeFunction implements RuntimeFunction {
   protected BufferedReader getBufferedReader(ExecutionContext executionContext) {
      return new BufferedReader(new InputStreamReader(executionContext.getConsoleInput()));
   }

   protected PrintStream getPrintStream(ExecutionContext executionContext) {
      return new PrintStream(executionContext.getConsoleOut());
   }
}
