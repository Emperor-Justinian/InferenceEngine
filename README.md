# InferenceEngine
COS30019 - Assignment 2 - Inference Engine

## Current capabilities
Currently, the program does the following:
1. Parses a TELL statement
1. Purges whitespace characters
1. Splits the statement into semicolon-separated strings
1. If the string represents a Horn clause, constructs a Horn clause out of this string, and adds it to a collection of Horn clauses (currently implemented as an ArrayList<HornClause>). Otherwise, the string is assumed to be a fact, and is added to a collection of facts (currently implemented as an ArrayList<String>).
1. Prints all Horn clauses and facts to System.out

## Command line arguments
1. The algorithm to use (this does nothing at the moment)
1. The path to the input file

## Notes
* The program currently ignores the ASK statement that follows the TELL statement.
* The program currently ignores the algorithm supplied in the command line arguments.
