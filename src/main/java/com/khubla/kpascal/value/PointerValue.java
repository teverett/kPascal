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
package com.khubla.kpascal.value;

import com.khubla.kpascal.exception.InvalidOperationException;
import com.khubla.kpascal.exception.NotImplementedException;
import com.khubla.kpascal.type.PointerType;
import com.khubla.kpascal.type.Type;

public class PointerValue implements Value {
   private final PointerType pointerType;
   private Value targetValue;

   public PointerValue(PointerType pointerType) {
      this.pointerType = pointerType;
   }

   @Override
   public Value add(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value and(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value deepCopy() {
      throw new NotImplementedException();
   }

   @Override
   public Value div(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue eq(Value v) {
      throw new InvalidOperationException();
   }

   public PointerType getPointerType() {
      return pointerType;
   }

   public Value getTargetValue() {
      return targetValue;
   }

   @Override
   public Type getType() {
      return pointerType;
   }

   @Override
   public BooleanValue gt(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue gte(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value idiv(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue in(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue lt(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue lte(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value mod(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value mult(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value neg() {
      throw new InvalidOperationException();
   }

   @Override
   public BooleanValue neq(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public Value not() {
      throw new InvalidOperationException();
   }

   @Override
   public Value or(Value v) {
      throw new InvalidOperationException();
   }

   @Override
   public void set(Value v) {
      throw new InvalidOperationException();
   }

   public void setTargetValue(Value targetValue) {
      this.targetValue = targetValue;
   }

   @Override
   public Value subtract(Value v) {
      throw new InvalidOperationException();
   }
}
