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
package com.khubla.kpascal.listener;

import java.util.List;

import com.khubla.kpascal.ExecutionContext;
import com.khubla.pascal.pascalParser;

public class ProgramHeadingListener extends AbstractPascalListener {
   private String programName = null;
   private List<String> identifiers = null;

   public ProgramHeadingListener(ExecutionContext executionContext) {
      super(executionContext);
   }

   @Override
   public void enterProgramHeading(pascalParser.ProgramHeadingContext ctx) {
      if (null != ctx.identifier()) {
         final IdentifierListener identifierListener = new IdentifierListener(getExecutionContext());
         identifierListener.enterIdentifier(ctx.identifier());
         programName = identifierListener.getIdentifier();
      }
      if (null != ctx.identifierList()) {
         final IdentifierListListener identifierListListener = new IdentifierListListener(getExecutionContext());
         identifierListListener.enterIdentifierList(ctx.identifierList());
         identifiers = identifierListListener.getIdentifiers();
      }
   }

   public List<String> getIdentifiers() {
      return identifiers;
   }

   public String getProgramName() {
      return programName;
   }

   public void setIdentifiers(List<String> identifiers) {
      this.identifiers = identifiers;
   }

   public void setProgramName(String programName) {
      this.programName = programName;
   }
}
