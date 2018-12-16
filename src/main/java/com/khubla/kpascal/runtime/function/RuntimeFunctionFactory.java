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
package com.khubla.kpascal.runtime.function;

import java.util.Hashtable;

import com.khubla.kpascal.runtime.function.conversion.ChrFunction;
import com.khubla.kpascal.runtime.function.conversion.FracFunction;
import com.khubla.kpascal.runtime.function.conversion.HiFunction;
import com.khubla.kpascal.runtime.function.conversion.IntFunction;
import com.khubla.kpascal.runtime.function.conversion.LoFunction;
import com.khubla.kpascal.runtime.function.conversion.OrdFunction;
import com.khubla.kpascal.runtime.function.conversion.RoundFunction;
import com.khubla.kpascal.runtime.function.conversion.SwapFunction;
import com.khubla.kpascal.runtime.function.conversion.TruncFunction;
import com.khubla.kpascal.runtime.function.file.ChDirFunction;
import com.khubla.kpascal.runtime.function.file.EraseFunction;
import com.khubla.kpascal.runtime.function.file.GetDirFunction;
import com.khubla.kpascal.runtime.function.file.MkDirFunction;
import com.khubla.kpascal.runtime.function.file.RenameFunction;
import com.khubla.kpascal.runtime.function.file.RmDirFunction;
import com.khubla.kpascal.runtime.function.io.ReadFunction;
import com.khubla.kpascal.runtime.function.io.ReadKeyFunction;
import com.khubla.kpascal.runtime.function.io.ReadlnFunction;
import com.khubla.kpascal.runtime.function.io.WriteFunction;
import com.khubla.kpascal.runtime.function.io.WritelnFunction;
import com.khubla.kpascal.runtime.function.math.AbsFunction;
import com.khubla.kpascal.runtime.function.math.ArcCosFunction;
import com.khubla.kpascal.runtime.function.math.ArcSinFunction;
import com.khubla.kpascal.runtime.function.math.ArcTanFunction;
import com.khubla.kpascal.runtime.function.math.CosFunction;
import com.khubla.kpascal.runtime.function.math.ExpFunction;
import com.khubla.kpascal.runtime.function.math.LnFunction;
import com.khubla.kpascal.runtime.function.math.OddFunction;
import com.khubla.kpascal.runtime.function.math.RandomFunction;
import com.khubla.kpascal.runtime.function.math.RandomizeFunction;
import com.khubla.kpascal.runtime.function.math.SinFunction;
import com.khubla.kpascal.runtime.function.math.SqrFunction;
import com.khubla.kpascal.runtime.function.math.SqrtFunction;
import com.khubla.kpascal.runtime.function.math.TanFunction;
import com.khubla.kpascal.runtime.function.misc.DelayFunction;
import com.khubla.kpascal.runtime.function.misc.HaltFunction;
import com.khubla.kpascal.runtime.function.misc.IntrFunction;
import com.khubla.kpascal.runtime.function.misc.PauseFunction;
import com.khubla.kpascal.runtime.function.misc.SizeOfFunction;
import com.khubla.kpascal.runtime.function.pointer.AddrFunction;
import com.khubla.kpascal.runtime.function.pointer.AddressFunction;
import com.khubla.kpascal.runtime.function.pointer.FillCharFunction;
import com.khubla.kpascal.runtime.function.pointer.MakePointerFunction;
import com.khubla.kpascal.runtime.function.pointer.Mem_To_VarFunction;
import com.khubla.kpascal.runtime.function.pointer.MoveFunction;
import com.khubla.kpascal.runtime.function.pointer.OfsFunction;
import com.khubla.kpascal.runtime.function.pointer.PeekFunction;
import com.khubla.kpascal.runtime.function.pointer.PokeFunction;
import com.khubla.kpascal.runtime.function.pointer.PtrFunction;
import com.khubla.kpascal.runtime.function.pointer.RawPointerFunction;
import com.khubla.kpascal.runtime.function.pointer.Var_To_MemFunction;
import com.khubla.kpascal.runtime.function.string.CopyFunction;
import com.khubla.kpascal.runtime.function.string.DeleteFunction;
import com.khubla.kpascal.runtime.function.string.InsertFunction;
import com.khubla.kpascal.runtime.function.string.LengthFunction;
import com.khubla.kpascal.runtime.function.string.PosFunction;
import com.khubla.kpascal.runtime.function.string.StrConcatFunction;
import com.khubla.kpascal.runtime.function.string.StrDeleteFunction;
import com.khubla.kpascal.runtime.function.string.StrFunction;
import com.khubla.kpascal.runtime.function.string.StrInsertFunction;
import com.khubla.kpascal.runtime.function.string.StrLenFunction;
import com.khubla.kpascal.runtime.function.string.StrScanFunction;
import com.khubla.kpascal.runtime.function.string.StrSizeFunction;
import com.khubla.kpascal.runtime.function.string.SubStrFunction;
import com.khubla.kpascal.runtime.function.string.ValFunction;

public class RuntimeFunctionFactory {
   private final Hashtable<String, RuntimeFunction> functions = new Hashtable<String, RuntimeFunction>();

   public RuntimeFunctionFactory() {
      /*
       * io
       */
      addFunction("writeln", new WritelnFunction());
      addFunction("write", new WriteFunction());
      addFunction("read", new ReadFunction());
      addFunction("readln", new ReadlnFunction());
      addFunction("readkey", new ReadKeyFunction());
      /*
       * math
       */
      addFunction("sin", new SinFunction());
      addFunction("cos", new CosFunction());
      addFunction("tan", new TanFunction());
      addFunction("arcsin", new ArcSinFunction());
      addFunction("arccos", new ArcCosFunction());
      addFunction("arctan", new ArcTanFunction());
      addFunction("abs", new AbsFunction());
      addFunction("sqr", new SqrFunction());
      addFunction("sqrt", new SqrtFunction());
      addFunction("random", new RandomFunction());
      addFunction("odd", new OddFunction());
      addFunction("ln", new LnFunction());
      addFunction("exp", new ExpFunction());
      addFunction("Randomize", new RandomizeFunction());
      /*
       * conversion
       */
      addFunction("round", new RoundFunction());
      addFunction("trunc", new TruncFunction());
      addFunction("chr", new ChrFunction());
      addFunction("ord", new OrdFunction());
      addFunction("Frac", new FracFunction());
      addFunction("Int", new IntFunction());
      addFunction("Hi", new HiFunction());
      addFunction("Lo", new LoFunction());
      addFunction("Swap", new SwapFunction());
      /*
       * string
       */
      addFunction("StrConcat", new StrConcatFunction());
      addFunction("StrDelete", new StrDeleteFunction());
      addFunction("StrInsert", new StrInsertFunction());
      addFunction("StrLen", new StrLenFunction());
      addFunction("StrScan", new StrScanFunction());
      addFunction("StrSize", new StrSizeFunction());
      addFunction("SubStr", new SubStrFunction());
      addFunction("Delete", new DeleteFunction());
      addFunction("Insert", new InsertFunction());
      addFunction("Copy", new CopyFunction());
      addFunction("Length", new LengthFunction());
      addFunction("Pos", new PosFunction());
      addFunction("Str", new StrFunction());
      addFunction("Val", new ValFunction());
      /*
       * pointer
       */
      addFunction("address", new AddressFunction());
      addFunction("Ofs", new OfsFunction());
      addFunction("MakePointer", new MakePointerFunction());
      addFunction("Addr", new AddrFunction());
      addFunction("RawPointer", new RawPointerFunction());
      addFunction("Ptr", new PtrFunction());
      addFunction("peek", new PeekFunction());
      addFunction("poke", new PokeFunction());
      addFunction("Var_To_Mem", new Var_To_MemFunction());
      addFunction("Mem_To_Var", new Mem_To_VarFunction());
      addFunction("Move", new MoveFunction());
      addFunction("FillChar", new FillCharFunction());
      /*
       * file
       */
      addFunction("ChDir", new ChDirFunction());
      addFunction("Erase", new EraseFunction());
      addFunction("GetDir", new GetDirFunction());
      addFunction("MkDir", new MkDirFunction());
      addFunction("RmDir", new RmDirFunction());
      addFunction("Rename", new RenameFunction());
      /*
       * misc
       */
      addFunction("pause", new PauseFunction());
      addFunction("SizeOf", new SizeOfFunction());
      addFunction("Delay", new DelayFunction());
      addFunction("Halt", new HaltFunction());
      addFunction("Intr", new IntrFunction());
   }

   private void addFunction(String name, RuntimeFunction runtimeFunction) {
      functions.put(name.toLowerCase(), runtimeFunction);
   }

   public RuntimeFunction getRuntimeFunction(String name) {
      final RuntimeFunction runtimeFunction = functions.get(name.toLowerCase());
      if (null != runtimeFunction) {
         return runtimeFunction;
      } else {
         return null;
      }
   }
}
