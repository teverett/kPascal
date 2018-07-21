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
import java.io.InputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExamples {
   public String runProgram(String mumpsfile, String input) throws Exception {
      final InputStream inputStream = Main.class.getResourceAsStream(mumpsfile);
      final ByteArrayOutputStream baos = new ByteArrayOutputStream();
      final Interpreter interpreter = new Interpreter(new ByteArrayInputStream(input.getBytes()), new PrintStream(baos));
      interpreter.run(inputStream);
      return baos.toString();
   }

   public void runProgramToConsole(String mumpsfile) throws Exception {
      final InputStream inputStream = Main.class.getResourceAsStream(mumpsfile);
      final Interpreter interpreter = new Interpreter(System.in, System.out);
      interpreter.run(inputStream);
   }

   @Test(enabled = true)
   public void testAdd() {
      try {
         final String output = runProgram("/add.pas", "");
         Assert.assertTrue(output.compareTo("'The array before call to adder:'12345'The array after call to adder:'246810") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testHelloWorld() {
      try {
         final String output = runProgram("/helloworld.pas", "");
         Assert.assertTrue(output.compareTo("41\n") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testIf() {
      try {
         final String output = runProgram("/if.pas", "50\n");
         Assert.assertTrue(output.compareTo("'Please enter an integer between 0 and 100'\n" + "'If this was a grade, you would receive a grade of:  ''F'") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
