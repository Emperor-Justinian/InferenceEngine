package inferenceEngine;

import java.util.ArrayList;

public class ForwardChaining extends Algorithm {
	/**
	 * @author Tim
	 *
	 */
	
	private ArrayList<HornClause> clauses;
	private ArrayList<String> facts;
	private String query;

	private ArrayList<String> outputFacts;
	
	public ForwardChaining()
	{
		setCode("FC");
		
		setLongName("Forward Chaining");
	}
	
	public ForwardChaining(KnowledgeBase aKb, String aToAsk) {
    super(aKb, aToAsk);
    
	    clauses = aKb.getClauses();
		facts = aKb.getFacts();
		query = aToAsk;

		outputFacts = new ArrayList<String>();

	    setCode("FC");
	    
	    setLongName("Forward Chaining");
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
			for ( int i = 0; i < outputFacts.size(); i++ )
			{
				output += ( outputFacts.get(i) );
				
				if (i < outputFacts.size() - 1)
				{
					output += ", ";
				}
			}
		}
		else
		{
			// else output "NO: (Query) could not be proven"
			output = "NO: " + query + " could not be proven.";
		}
		return output;				
	}
	
	@Override
	public boolean CheckFacts(){
		
		// search through each fact until there are none left
		while ( !facts.isEmpty() ){
			
			// pop off the first fact to explore
			String aFact = facts.remove(0);
			
			// add it to the list of facts that will be used to output (if true)
			outputFacts.add( aFact );
			
			// if the current fact matches the proposition symbol,  
			// then it has been successfully proven, and the search is over
			
			if ( aFact.equals( query ) )
			{
				return true;
			}
			
			// otherwise, compare the current fact with every literal in each horn clause
			// if it finds a match, then remove it as a literal from that horn clause
			for ( int i = 0; i < clauses.size(); i++ )
			{
				for ( int j = 0; j < clauses.get(i).literalCount(); j++ )
				{
					if ( aFact.equals( clauses.get(i).getLiteralsAtIndex(j) ) )
					{
						clauses.get(i).removeLiteral(aFact);
					}
				}
			}
			
			// get the literal count of every horn clause: add the entailed literal
			// from the clause to the end of the list of facts and remove the clause
			// from the list of clauses to stop it from discovering the same fact over
			// and over
			for ( int i = 0; i < clauses.size(); i++ )
			{
				if ( clauses.get(i).literalCount() == 0 )
				{
					facts.add( clauses.get(i).getEntailedLiteral() );
					
					clauses.remove(i);
				}
			}
		}
		return false;
	}
}
