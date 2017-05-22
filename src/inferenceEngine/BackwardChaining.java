package inferenceEngine;

import java.util.ArrayList;

public class BackwardChaining extends Algorithm {
	/**
	 * @author Tim
	 *
	 */
	
	private ArrayList<HornClause> clauses;
	private ArrayList<String> facts;
	private String query;

	private ArrayList<String> outputFacts;
	private ArrayList<String> queries;
	
	public BackwardChaining()
	{
		setCode("BC");
	}
	
	public BackwardChaining(KnowledgeBase aKb, String aToAsk) {
    super(aKb, aToAsk);
    
	    	clauses = aKb.getClauses();
		facts = aKb.getFacts();
		query = aToAsk;
		
		queries = new ArrayList<String>();
		outputFacts = new ArrayList<String>();
	
	    	setCode("BC");
	}
	
	@Override
	public String testAskStatement()
	{
		String output = "";
		
		// CheckFacts check's whether the query can be proven 
		if ( CheckFacts() )
		{
			// if so, output YES:
			output = "YES: ";
			
			// as well as each fact that is discovered
			for ( int i = outputFacts.size() - 1; i >= 0; i-- )
			{
				output += ( outputFacts.get(i) );
				
				if (i != 0)
				{
					output += ", ";
				}
			}
		}
		else
		{
			// else output "(Query) could not be proven"
			output = query + " could not be proven.";
		}
		return output;		
	}

	@Override
	public boolean CheckFacts(){
		
		// Put the query on the top of queries
		queries.add( query );
		
		// while there are still queries to be proven
		while ( queries.size() > 0 )
		{
			// take the first query off of queries to be checked 
			String currentQuery = queries.remove(queries.size() - 1);

			// add it to the list of queries that will be used to output (if true)
			outputFacts.add( currentQuery );

			// Compare currentQuery with every fact in Facts:
			// if it matches a fact then the current loop can end
			if( ! CompareToFacts( currentQuery ) )
			{
				// if not, compare currentQuery with every entailed literal
				// if it matches, add the literal(s) from the left
				// of the entailment onto queries
				if( ! CompareToClauses( currentQuery ) )
				{
					// if currentQuery still didn’t find a match then 
					// the search has failed 
					return false;
				}
			}
		}
		// when queries is empty, it means there are no more queries to prove,
		// so the search was a success
		return true;
	}
	
	public boolean CompareToFacts( String aQuery )
	{
		for ( int i = 0; i < facts.size(); i++ )
		{
			if ( aQuery.equals( facts.get(i) ) )
			{
				return true;
			}
		}
		return false;
	}

	public boolean CompareToClauses( String aQuery )
	{
		boolean result = false;

		for ( int i = 0; i < clauses.size(); i++ )
		{
			if ( aQuery.equals( clauses.get(i).getEntailedLiteral() ) )
			{
				result = true;

				for ( int j = 0; j < clauses.get(i).literalCount(); j++ )
				{
					queries.add( clauses.get(i).getLiteralsAtIndex(j) );
				}
			}
		}
		return result;
	}
}