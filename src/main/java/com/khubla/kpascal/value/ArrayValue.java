package com.khubla.kpascal.value;

import com.khubla.kpascal.exception.InterpreterException;
import com.khubla.kpascal.type.ArrayType;
import com.khubla.kpascal.type.Type;

/**
 * note that Pascal arrays index from 1, not 0
 */
public class ArrayValue implements Value {
   /**
    * values
    */
   private final Value[] values;
   /**
    * size
    */
   private final int size;
   /**
    * lower bound
    */
   private final int lbound;
   /**
    * upper bound
    */
   private final int ubound;
   /**
    * type
    */
   private final ArrayType arrayType;

   public ArrayValue(ArrayType arrayType) {
      this.arrayType = arrayType;
      lbound = arrayType.indices.get(0).lowerRange.asInteger();
      ubound = arrayType.indices.get(0).upperRange.asInteger();
      size = (ubound - lbound) + 1;
      values = new Value[size];
      for (int i = 0; i < size; i++) {
         values[i] = arrayType.getComponentType().createValue();
      }
   }

   public ArrayType getArrayType() {
      return arrayType;
   }

   @Override
   public Type getType() {
      return arrayType;
   }

   public Value getValue(int idx) throws InterpreterException {
      if ((idx >= lbound) && (idx <= ubound)) {
         return values[idx - 1];
      } else {
         throw new InterpreterException("Index '" + idx + "' out of range for array of size '" + size + "'");
      }
   }

   public void setValue(int idx, Value value) throws InterpreterException {
      if ((idx >= lbound) && (idx <= ubound)) {
         values[idx - 1] = value;
      } else {
         throw new InterpreterException("Index '" + idx + "' out of range for array of size '" + size + "'");
      }
   }
}
