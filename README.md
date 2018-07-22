[![Travis](https://travis-ci.org/teverett/kPascal.svg?branch=master)](https://travis-ci.org/teverett/kPascal)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4990c2bde63b4380aada591d17334fd7)](https://www.codacy.com/app/teverett/kPascal?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=teverett/kPascal&amp;utm_campaign=Badge_Grade)

kPascal
========

A Pascal Interpreter written in Java.  kPascal is intended to be used as either a command-line Java interpreter, or as an embedded Pascal engine inside J2EE applications.


Maven Coordinates
-------------------

```
<groupId>com.khubla.kpascal</groupId>
<artifactId>kpascal</artifactId>
<version>1.0-SNAPSHOT</version>
<packaging>jar</packaging>
```

License
---------

kPascal is distributed until the GPL v3. For more information please see the [GPL](http://www.gnu.org/licenses/gpl.txt).


The grammar
---------

kPascal uses [Antlr](http://www.antlr.org/).  The Pascal grammar is [here](https://github.com/antlr/grammars-v4/blob/master/pascal/pascal.g4)


Using kPascal in code
---------

From a file:

````
FileInputStream fileInputStream = new FileInputStream("myprogram.pas");
Interpreter interpreter = new Interpreter();
interpreter.run(fileInputStream);
````

From a string:


````
String myProgram = "program HelloWorld; begin writeln('hello'); end."
ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(myProgram.getBytes());
Interpreter interpreter = new Interpreter();
interpreter.run(byteArrayInputStream);
````


Using kPascal from the command line
---------

````
java -jar kpascal.jar --file=myprogram.pas

````