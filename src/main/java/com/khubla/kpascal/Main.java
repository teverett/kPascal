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

import java.io.FileInputStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * @author Tom Everett
 */
class Main {
   /**
    * file option
    */
   private static final String FILE_OPTION = "file";

   public static void main(String[] args) {
      System.out.println("khubla.com kPascal");
      /*
       * options
       */
      final Options options = new Options();
      Option oo = Option.builder().argName(FILE_OPTION).longOpt(FILE_OPTION).type(String.class).hasArg().required(true).desc("Pascal file").build();
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
      try {
         final String filename = cmd.getOptionValue(FILE_OPTION);
         if (null != filename) {
            FileInputStream fis = new FileInputStream(filename);
            final Interpreter interpreter = new Interpreter();
            interpreter.run(fis);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}