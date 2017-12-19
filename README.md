Project 1 for CSC365.  Assignment instructions are provided below.

Assignment 1: This assignment asks you to create a data categorization program.
The program reads 10 (or more) data sources -- web pages or other (text) data accessible with a url. The urls can be hardwired in the program, and (re)read when the program starts. Or you can use a site providing Random URLs. For each source, the program maintains at least the frequencies of keys; normally space/symbol-delimited words, but possibly other features, as discussed in class. It does so separately (or with different weights) for normal text vs ( metatags).
The user can enter any other URL, and the program reports which other known source is most closely related, using a similarity metric such as weighted cosine vector.
The presentation details are up to you. The implementation restrictions are:

Use library Collections for all data structures except for a custom hash table class you implement for maintaining per-source key frequencies. Read through the Collections tutorial first.
Use Swing, JavaFX, or Android components for the GUI. For Swing, read through the relevant parts of the Swing tutorial first.
Use library networking components for accessing web pages. See the Java networking with URLs tutorial. You can also use a (non-JDK) library HTML parser (such as jSoup), and/or word stemming components. But just using simple manual word extraction is also OK.
