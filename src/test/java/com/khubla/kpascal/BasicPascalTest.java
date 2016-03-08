package com.khubla.kpascal;

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
import java.io.InputStream;

import org.testng.Assert;

import com.khubla.kpascal.interpreter.PascalInterpreter;

public abstract class BasicPascalTest {
   protected void testPascalProgram(String pascalProgramPath) {
      try {
         final InputStream is = TestAdd.class.getResourceAsStream(pascalProgramPath);
         final PascalInterpreter pascalInterpreter = new PascalInterpreter(is, System.in, System.out);
         pascalInterpreter.run();
         pascalInterpreter.getRootBlock().getContext().getCurrentScope().reportVariables();
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
