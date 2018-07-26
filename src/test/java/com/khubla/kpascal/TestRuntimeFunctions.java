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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.kpascal.runtime.function.io.ReadlnFunction;
import com.khubla.kpascal.runtime.function.io.WritelnFunction;
import com.khubla.kpascal.value.IntegerValue;
import com.khubla.kpascal.value.RealValue;
import com.khubla.kpascal.value.StringValue;
import com.khubla.kpascal.value.Value;

public class TestRuntimeFunctions {
   @Test(enabled = true)
   public void testReadln() {
      try {
         final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new String("10\n").getBytes());
         final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         final ExecutionContext executionContext = new ExecutionContext(byteArrayInputStream, byteArrayOutputStream);
         final ReadlnFunction readlnFunction = new ReadlnFunction();
         final List<Value> args = new ArrayList<Value>();
         final IntegerValue iv = new IntegerValue(0);
         args.add(iv);
         readlnFunction.execute(executionContext, args);
         Assert.assertTrue(iv.getValue() == 10);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testWriteln() {
      try {
         final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new String("").getBytes());
         final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         final ExecutionContext executionContext = new ExecutionContext(byteArrayInputStream, byteArrayOutputStream);
         final WritelnFunction writelnFunction = new WritelnFunction();
         final List<Value> args = new ArrayList<Value>();
         args.add(new IntegerValue(100));
         writelnFunction.execute(executionContext, args);
         final String output = byteArrayOutputStream.toString();
         Assert.assertTrue(output.compareTo("100\n") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testWritelnMultiple() {
      try {
         final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new String("").getBytes());
         final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         final ExecutionContext executionContext = new ExecutionContext(byteArrayInputStream, byteArrayOutputStream);
         final WritelnFunction writelnFunction = new WritelnFunction();
         final List<Value> args = new ArrayList<Value>();
         args.add(new IntegerValue(100));
         args.add(new IntegerValue(107));
         args.add(new RealValue(12.2));
         args.add(new StringValue("cat"));
         writelnFunction.execute(executionContext, args);
         final String output = byteArrayOutputStream.toString();
         Assert.assertTrue(output.compareTo("10010712.2cat\n") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
