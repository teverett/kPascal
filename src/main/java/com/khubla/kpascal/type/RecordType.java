package com.khubla.kpascal.type;

import java.util.List;

import com.khubla.kpascal.value.RecordValue;
import com.khubla.kpascal.value.Value;

public class RecordType implements Type {
   public static class Field {
      private final Type type;
      private final String name;

      public Field(String name, Type type) {
         this.name = name;
         this.type = type;
      }

      public String getName() {
         return name;
      }

      public Type getType() {
         return type;
      }
   }

   private final List<Field> fields;

   public RecordType(List<Field> fields) {
      this.fields = fields;
   }

   @Override
   public boolean builtIn() {
      return false;
   }

   @Override
   public Value createValue() {
      return new RecordValue(this);
   }

   public List<Field> getFields() {
      return fields;
   }
}
