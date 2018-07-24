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
         Assert.assertTrue(output.compareTo("value of a: \n" + "10\n" + "value of a: \n" + "11\n" + "value of a: \n" + "12\n" + "value of a: \n" + "13\n" + "value of a: \n" + "14\n" + "value of a: \n"
               + "15\n" + "value of a: \n" + "16\n" + "value of a: \n" + "17\n" + "value of a: \n" + "18\n" + "value of a: \n" + "19\n" + "value of a: \n" + "20\n") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
