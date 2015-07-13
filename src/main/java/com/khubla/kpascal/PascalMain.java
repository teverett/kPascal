package com.khubla.kpascal;

/*
 * kPascal Copyright 2012, khubla.com
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
import java.io.FileInputStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import com.khubla.kpascal.interpreter.PascalInterpreter;

/**
 * @author tom
 */
public class PascalMain {
   /**
    * file option
    */
   private static final String FILE_OPTION = "file";

   public static void main(String[] args) {
      try {
         System.out.println("khubla.com kPascal Interpreter");
         /*
          * options
          */
         final Options options = new Options();
         final Option oo = Option.builder().argName(FILE_OPTION).longOpt(FILE_OPTION).type(String.class).hasArg().required(true).desc("file to compile").build();
         options.addOption(oo);
         /*
          * parse
          */
         final CommandLineParser parser = new DefaultParser();
         CommandLine cmd = null;
         try {
            cmd = parser.parse(options, args);
         } catch (final Exception e) {
            e.printStackTrace();
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("posix", options);
            System.exit(0);
         }
         /*
          * get the file
          */
         final String filename = cmd.getOptionValue(FILE_OPTION);
         if (null != filename) {
            /*
             * filename
             */
            final String pasFileName = System.getProperty("user.dir") + "/" + filename;
            final File fl = new File(pasFileName);
            if (true == fl.exists()) {
               /*
                * show the filename
                */
               System.out.println("Compiling: " + fl.getCanonicalFile());
               /*
                * interpreter
                */
               final PascalInterpreter pascalInterpreter = new PascalInterpreter(new FileInputStream(fl), System.in, System.out);
               pascalInterpreter.run();
            } else {
               throw new Exception("Unable to find: '" + pasFileName + "'");
            }
         } else {
            throw new Exception("File was not supplied");
         }
      } catch (final Exception e) {
         e.printStackTrace();
      }
   }
}