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
         Assert.assertTrue(output.compareTo("The array before call to adder:12345The array after call to adder:246810") == 0);
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
         Assert.assertTrue(output.compareTo("Please enter an integer between 0 and 100\n" + "If this was a grade, you would receive a grade of:  F") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testFact() {
      try {
         final String output = runProgram("/fact.pas", "");
         Assert.assertTrue(output.compareTo("Factorial of \n" + "5\n" + " is \n" + "120\n") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = false)
   public void testNesting() {
      try {
         runProgramToConsole("/nesting.pas");
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testPassFail() {
      try {
         final String output = runProgram("/passfail.pas", "49\n100\n");
         Assert.assertTrue(output.compareTo("Please type the student's actual mark: \n" + "Please type the total possible mark of the exam : \n" + "Fail\n") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testPointer() {
      try {
         runProgramToConsole("/pointer.pas");
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = false)
   public void testSet() {
      try {
         runProgramToConsole("/set.pas");
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = false)
   public void testSet2() {
      try {
         runProgramToConsole("/set2.pas");
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = false)
   public void testQuadratic() {
      try {
         final String output = runProgram("/quadratic.pas", "13\n13\n14\n");
         Assert.assertTrue(output.compareTo("A = B = C = x1 = (\n" + "-84.5\n" + ",\n" + "153.68067542797954\n" + ")\n") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = false)
   public void testCalc() {
      try {
         runProgramToConsole("/calc.pas");
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = false)
   public void testGussinggame() {
      try {
         runProgramToConsole("/guessinggame.pas");
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
