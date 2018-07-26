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

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestStatements extends AbstractTest {
   @Test(enabled = true)
   public void testForLoop() {
      try {
         final String output = runProgram("/statements/forloop.pas", "");
         Assert.assertTrue(output.compareTo("value of a: 10\n" + "value of a: 11\n" + "value of a: 12\n" + "value of a: 13\n" + "value of a: 14\n" + "value of a: 15\n" + "value of a: 16\n"
               + "value of a: 17\n" + "value of a: 18\n" + "value of a: 19\n" + "value of a: 20\n") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testRepeatLoop() {
      try {
         final String output = runProgram("/statements/repeatloop.pas", "");
         Assert.assertTrue(output.compareTo("value of a: 10\n" + "value of a: 11\n" + "value of a: 12\n" + "value of a: 13\n" + "value of a: 14\n" + "value of a: 15\n" + "value of a: 16\n"
               + "value of a: 17\n" + "value of a: 18\n" + "value of a: 19\n") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testWhileLoop() {
      try {
         final String output = runProgram("/statements/whileloop.pas", "");
         Assert.assertTrue(output.compareTo("value of a: 10\n" + "value of a: 11\n" + "value of a: 12\n" + "value of a: 13\n" + "value of a: 14\n" + "value of a: 15\n" + "value of a: 16\n"
               + "value of a: 17\n" + "value of a: 18\n" + "value of a: 19\n") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
