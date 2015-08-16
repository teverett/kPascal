package com.khubla.kpascal;

import java.io.File;
import java.io.FileInputStream;
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
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.kpascal.interpreter.PascalInterpreter;

public class TestInterpreter {
   private void doTest(String name) {
      try {
         System.out.println("Parsing: " + name);
         final InputStream is = new FileInputStream(name);
         final PascalInterpreter pascalInterpreter = new PascalInterpreter(is, System.in, System.out);
         pascalInterpreter.run();
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testExampleModels() {
      try {
         final List<File> files = FileUtil.getAllFiles("src/test/resources/");
         if (null != files) {
            for (final File file : files) {
               doTest(file.getAbsolutePath());
            }
         }
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
