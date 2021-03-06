# InferenceEngine
Swinburne University of Technology - COS30019 (Introduction to Artificial Intelligence) - Assignment 2 - Inference Engine

Since we used GitHub for collaboration on this project, you can also find it [here](https://github.com/Emperor-Justinian/InferenceEngine).

## Student Details
* Andrew Piubellini (9988416) a.k.a. "Emperor-Justinian" on GitHub
* Timothy Quill (100997474) a.k.a. "TimothyQuill" on GitHub

## Features/Bugs/Missing
* Forward Chaining - No bugs, program produced the correctly formatted output with all tested examples. 
* Backward Chaining - No bugs, program worked well with all tested examples, finding the shortest path to prove the query and producing the correctly formatted output
* Truth Table - No bugs, program produced the correctly formatted output with all tested examples.

## Test cases
### Test Case #1 - starting with a simple set of horn-clauses
TELL
a => b; b =>c; c => d; a;
ASK
d

FC output: YES: a, b, c d

BC output: YES: a, b, c d

TT output: YES: 9

### Test Case #2 - when the query can’t be proven
TELL
a => b; b =>c; c => d;
ASK
d

FC output: NO

BC output: NO

TT output: YES: 5

**a bug was discovered!**

The output should be “NO”. It was discovered that this was because the truth table was returning true when the query was satisfiable, rather than being valid.

This was resolved by:

1. for every row in which the query was false, the entire row is false.
2. for each row comparing the result of the query with the KB, if the query is false in the same column that the KB is true then the program returns a result of “NO”. With this new addition to the code the program produced the correct output:

TT output: NO: d could not be proven

### Test Case #3 - when the query is a fact
TELL
a => b; b =>c; c => d; d;
ASK
d

FC output: YES: d

BC output: YES: d

TT output: YES: 4

### Test Case #4 - when the set contains irrelevant horn-clauses
TELL
a => b; b =>c; c => d; e=>g;l=>m; a;
ASK
d

FC output: YES:  a, b, c d

BC output: YES:  a, b, c d

TT output: YES: 9

### Test Case #5 - a set horn-clauses that contains both one or two literals on the left side
TELL
a&b => c; c&d = > e; e&f => g; h => i; i =>b; b => d; j => f; a; h; j;
ASK
g

FC output: YES: a, h, j, i, f, b, c, d, e, g

BC output: YES: h, h, i, i, b, b, a, j, d, c, f, e, g

**a bug was discovered!**

The output is incorrect because the search explored some of the literals (h, i, and b) multiple times, which is an unnecessary expense.

The cause of the error was identified as relating to when a literal appears in two different horn clauses on the left side of the entailment. In this case it was b, which appeared in a&b => c and b => d.

To solve this problem, a small function was written that checks if a newly discovered literal has already been discovered before, and if so, ignores it. With this new addition to the code the program produced the correct output:

BC output: YES: j, h, i, f, d, b, a, c, e, g

TT output: YES: 1

### Test Case #6 - an entailed literal is entailed by itself
TELL
a&b => c; c&d = > e; e&f => g; e&f => f; h => i; i =>b; b => d; j => f; a; h; j;
ASK
g

FC: YES: a, h, j, i, f, b, c, d, e, g

BC: no output was returned

**a bug was discovered!**

The bug was caused by this horn-clause: e&f => f, which requires f to be proven by itself.

This caused the program to get stuck in an infinite loop as it constantly added and removed f from the queries array-list.

This error was solved by creating a method that checks if a literal has been added before, and if so, ignores it.

With this addition to the program, the new output is:

BC output: YES: j, h, i, f, d, b, a, c, e, g

TT output: YES: 1

### Test Case #7 - using the example from the assignment outline
TELL
p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;
ASK
d

FC output: YES: a, b, p2, p3, p1, d

BC output: YES: p2, p3, p1, d

TT output: YES: 3


## Acknowledgements/Resources

* Credit to [Oracle's Java documentation](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html) for an example on how to work with FileReaders and BufferedReaders. This was useful in the Main and KnowledgeBase classes.

* Credit to [Kevin Bowersox's StackOverflow answer](https://stackoverflow.com/questions/15633228/how-to-remove-all-white-spaces-in-java) for an example on how to remove whitespace from a string using a regular expression. This was useful when purging whitespace in the KnowledgeBase.readInput(BufferedReader reader) method.

* Credit to [this discussion on StackOverflow](http://stackoverflow.com/questions/207947/how-do-i-get-a-platform-dependent-new-line-character) for suggesting the use of System.lineSeparator() to get platform-specific line separators (i.e. LF on Linux/macOS, CRLF on Windows). This was useful in the KnowledgeBase.toString() method, to prevent cross-platform compatibility issues (since the program was developed on Linux and macOS, but needs to also work on Windows 7).

* Credit to [jonathan-stafford's StackOverflow answer](https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist) for an example of how to use a hash-set to cleanse an array-list of duplicate values. This was useful in the Truth Table class to make sure every variable was included in an array list of variables only once.

* Credit to [Dhass's answer](https://careercup.com/question?id=17632666) for an example of how to populate a truth table grid using every possible combination of T and F row each row.


## Notes
TODO

## Summary report
In general, both Andrew and Tim contributed to most elements of the assignment, but Andrew worked more on the programming side, while Tim worked more on the AI algorithm side.

Here is a more specific summary of each member's contributions:

* Andrew and Tim met in person on several occasions, to discuss how the AI would structure its data, and how the algorithms would be implemented.
* Tim did online research into how to implement truth tables, forward chaining and backward chaining.
* Andrew implemented the Main class, the KnowledgeBase and HornClause classes, and the abstract Algorithm class.
* Tim implemented the TruthTable, ForwardChaining and BackwardChaining classes.

For even more detail, see the commit history on GitHub.

**Andrew's contribution**: 50%

**Tim's contribution**: 50%
