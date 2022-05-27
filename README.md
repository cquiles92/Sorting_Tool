# Sorting_Tool
HyperSkill Project #17

https://hyperskill.org/projects/45

Source Directory:

Sorting_Tool/Sorting Tool/task/src/sorting/


This program takes inputs through the console and sorts them based off the type of data specified and by a specified sorting. This program can take inputs as either (long) integers, or as Strings. After the specified input is given, the console input must be closed with the EOF command (CTRL + Z in Windows; CTRL + D in UNIX\MAC). The String input can then be subclassed into either by String tokens, or as a literal String line. The handling of different datatypes and different sorting techniques is solved using a Strategy pattern. The built in sorting in this program, should evaluate either by the number of same elements counted or by natural order. If the data is sorted by number of elements counted, it will display a percentage out of the total inputs how many times the given input was given. This program also has support for certain Command Line arguments on execution as listed below.

**Command Line argument List:
-dataType, -sortingType, -inputFile, -outputFile

The -dataType argument requires the one of the following parameters: LONG, LINE, WORD. (The default is WORD aka String Tokens).

The -sortingType argument requires one of the following parameters: natural, byCount (The default is natural aka natural ordering).

The -inputFile argument takes a filename and requires a file that can be opened and read and expects the arguments in the same fashion as writing to the console.

The -outputFile argument takes a filename and requires a file that can be created and will output all the console output to the new file.
