README for PageRank

Author: Edrei Chua
Created on: 03/02/2016

*********** DIRECTORY STRUCTURE ***********

There are a few important files in this directory:

Report (directory for report)
    PageRank.pdf (detailed documentation of the code)
    PageRank.tex (tex file)
src (directory for source code)
    > PageRank.java
    > PageRankDriver.java
    > Graph.java
    > Matrix.java
README.txt
simple.txt
medium.txt
dartcsclasses.txt


*********** HOW TO START THE DEFAULT PROGRAM ***********

To start the program, compile all the .java files.

To run the program, run PageRankDriver.java.

The default setup runs PageRank on the file dartcsclass.txt with dangling nodes correction and random surf
jumping with damping factor 0.15. It uses the power method to compute eigenvector, with EPSILON = 0.0001
and maximum number of tries = 1000. Results are presented in sorted (descending) order of rank by default.

*********** ADDITIONAL FUNCTIONALITY ***********

To change the default setup for PageRank, toggle the boolean constants isDangling and isJump in
PageRankDriver.java.

To change the damping factor for the random surf jumping, change the value of the constant
DAMPING in PageRank.java

To change the parameters for power method, change the values of EPSILON and MAXTRIES in PageRank.java.

To change the default file used, change filename parameter of the constructor in PageRankDriver.java to
one of the following: DartmouthCSClasses ("dartcsclass.txt"), Simple ("simple.txt) or Medium ("medium.txt")

To present the information in sorted ID order of vertices, toggle isSorted in PageRankDriver.java to False.
To present the information in sorted (descending) order of rank, toggle isSorted to True.

