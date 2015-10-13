package com.khubla.kpascal;

import java.io.InputStream;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.kpascal.interpreter.PascalInterpreter;

/*
* kPascal Copyright 2015, khubla.com
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
public class TestHelloWorld {
   @Test(enabled = true)
   public void testHelloWorld() {
      try {
         InputStream is = TestHelloWorld.class.getResourceAsStream("/helloworld.pas");
         final PascalInterpreter pascalInterpreter = new PascalInterpreter(is, System.in, System.out);
         pascalInterpreter.run();
         pascalInterpreter.getContext().getCurrentScope().reportVariables();
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
