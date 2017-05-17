# InferenceEngine
COS30019 - Assignment 2 - Inference Engine

## Current capabilities
Currently, the program does the following:
1. Parses a TELL statement
1. Purges whitespace characters
1. Splits the statement into semicolon-separated strings
1. If the string represents a Horn clause, constructs a Horn clause out of this string, and adds it to a collection of Horn clauses (currently implemented as an ArrayList<HornClause>). Otherwise, the string is assumed to be a fact, and is added to a collection of facts (currently implemented as an ArrayList<String>).
1. Prints all Horn clauses and facts to System.out
1. Parses an ASK statement
1. Passes the knowledge base and ASK statement to the algorithm that the user selected
1. Prints the (boolean) result of running the algorithm that the user selected, to System.out

## Command line arguments
1. The algorithm to use
1. The path to the input file

## Notes
* Currently, the only algorithm present in the codebase is the truth table algorithm (Code: "TT"). However, this algorithm is not yet properly implemented, and simply returns false to allow for testing.
