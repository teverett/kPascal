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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
   /**
    * find files
    */
   public static List<File> getAllFiles(String dir) throws Exception {
      return getAllFiles(dir, null);
   }

   /**
    * find files
    */
   public static List<File> getAllFiles(String dir, String extension) {
      final List<File> ret = new ArrayList<File>();
      final File file = new File(dir);
      if (file.exists()) {
         final String[] list = file.list();
         for (int i = 0; i < list.length; i++) {
            final String fileName = dir + "/" + list[i];
            final File f2 = new File(fileName);
            if (false == f2.isHidden()) {
               if (f2.isDirectory()) {
                  ret.addAll(getAllFiles(fileName, extension));
               } else {
                  if (null != extension) {
                     if (f2.getName().endsWith(extension)) {
                        ret.add(f2);
                     }
                  } else {
                     ret.add(f2);
                  }
               }
            }
         }
         return ret;
      } else {
         return null;
      }
   }
}
