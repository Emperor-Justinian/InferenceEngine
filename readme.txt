# InferenceEngine
COS30019 - Assignment 2 - Inference Engine

## Student Details
* Andrew Piubellini (9988416)
* Timothy Quill (100997474)

## Features/Bugs/Missing
* Forward Chaining - No bugs, program produced the correctly formatted output with all tested examples. 
* Backward Chaining - No bugs, program worked well with all tested examples, finding the shortest path to prove the query and producing the correctly formatted output
* Truth Table - No bugs, program produced the correctly formatted output with all tested examples.

## Test cases
* Test Case #1 - starting with a simple set of horn-clauses
TELL
a => b; b =>c; c => d; a;
ASK
d
FC output: YES: a, b, c d
BC output: YES: a, b, c d
TT output: YES: 9

* Test Case #2 - when the query can’t be proven
TELL
a => b; b =>c; c => d;
ASK
d
FC output: NO
BC output: NO
TT output: YES: 5

* Test Case #3 - when the query is a fact
TELL
a => b; b =>c; c => d; d;
ASK
d
FC output: YES: d
BC output: YES: d
TT output: YES: 4

* Test Case #4 - when the set contains irrelevant horn-clauses
TELL
a => b; b =>c; c => d; e=>g;l=>m; a;
ASK
d
FC output: YES:  a, b, c d
BC output: YES:  a, b, c d
TT output: YES: 9

* Test Case #5 - a set horn-clauses that contains both one or two literals on the left side
TELL
a&b => c; c&d = > e; e&f => g; h => i; i =>b; b => d; j => f; a; h; j;
ASK
g
FC output: YES: a, h, j, i, f, b, c, d, e, g
BC output: YES: h, h, i, i, b, b, a, j, d, c, f, e, g
*** a bug was discovered! *** It is incorrect because the search explored some of the literals (h, i, and b) multiple times, which is an unnecessary expense. The cause of the error was identified as relating to when a literal appears in two different horn clauses on the left side of the entailment. In this case it was b, which appeared in a&b => c and b => d.
To solve this problem, a small function was written that checks if a newly discovered literal has already been discovered before, and if so, ignores it. With this new addition to the code the program produced the correct output:
BC output: YES: h, i, b, a, j, d, c, f, e, g
TT output: YES: 1

* Test Case #6 - using the example from the assignment outline
TELL
p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;
ASK
d
FC output: YES: a, b, p2, p3, p1, d
BC output: YES: p2, p3, p1, d
TT output: YES: 3

## Acknowledgements/Resources

* Credit to jonathan-stafford's answer at stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist for an example of how to use a hash-set to cleanse an array-list of duplicate values. This was useful in the Truth Table class to make sure every variable was included in an array list of variables only once.

* Credit to Dhass's answer at careercup.com/question?id=17632666 for an example of how to populate a truth table grid using every possible combination of T and F row each row.


## Notes
TODO

## Summary report
In general, both Andrew and Tim contributed to most elements of the assignment, but Andrew worked more on the programming side, while Tim worked more on the AI algorithm side.

Here is a more specific summary of each member's contributions:
* Andrew and Tim met in person on several occasions, to discuss how the AI would structure its data, and how the algorithms would be implemented.
* Tim did online research into how to implement truth tables, forward chaining and backward chaining.
* Andrew implemented the Main class, the KnowledgeBase and HornClause classes, and the abstract Algorithm class.
* Tim implemented the TruthTable, ForwardChaining and BackwardChaining classes.

**Andrew's contribution**: 50%

**Tim's contribution**: 50%
