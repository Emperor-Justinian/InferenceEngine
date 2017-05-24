package inferenceEngine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TruthTable extends Algorithm {
	/**
	 * @author Tim
	 *
	 */
	
	private ArrayList < HornClause > clauses;
	private ArrayList < String > facts;
	private String query;

	private ArrayList < String > variables;
	private int colNums;
	private int rowNums;
	private boolean[ ][ ] grid;
	private boolean[ ] formulaResult;
	private int[ ][ ] literalIndex;
	private int[ ] factIndex;
	private int[ ] entailed;
	private boolean[ ] queryResult;
	private int queryIndex;
	private int count;
	
	public TruthTable( )
	{
	    setCode("TT");
	    
	    setLongName("Truth Table");
	}
	
	public TruthTable( KnowledgeBase aKb, String aToAsk ) {
    super( aKb, aToAsk );
    
	    clauses = aKb.getClauses( );
		facts = aKb.getFacts( );
		query = aToAsk;
		
		variables = new ArrayList< String > ( );
		
		getVariables( );

		// one column for every literal
		colNums = variables.size( );

		// 2 to the power of n rows equals the number of variables squared
		rowNums = ( ( int ) Math.pow ( 2, ( variables.size( ) ) ) );

		// stores the boolean value of each variable in a row
		// each row contains an array of boolean values for each variable
		grid = new boolean[ rowNums ][ colNums ];

		// a final column that determines the result of each row
		formulaResult = new boolean[ rowNums ];
		
		for ( int i = 0; i < rowNums; i++ )
		{
			formulaResult[ i ] = true;
		}

		literalIndex = new int[ clauses.size( ) ][ 2 ];

		factIndex = new int[ facts.size( ) ];

		entailed = new int[ clauses.size( ) ];
		
		queryResult = new boolean[ rowNums ];
		
		queryIndex = 0;

		count = 0;

		PopulateGrid( );

		// get the column index for every fact in the TT grid
		GetColumnIndexOfFacts( );
		
		// get the column index for every literal in the TT grid
		GetColumnIndexOfLiterals( );

	    setCode( "TT" );
	    
	    setLongName("Truth Table");
	}
	
	@Override
	public String testAskStatement( )
	{
		String output = "";
		
		// CheckFacts check's whether the query can be proven 
		if ( CheckFacts( ) )
		{
			// if so, output YES:
			output = "YES: " + count;
		}
		
		else
		{
			// else output "(Query) could not be proven"
			output = "NO: " + query + " could not be proven.";
		}
		
		return output;			
	}

	@Override
	public boolean CheckFacts( )
	{
		// check the state of each fact for every row: if any of the facts
		//  in a row are false then the entire row is automatically false
		for ( int i = 0; i < rowNums; i++ )
		{
			for ( int j = 0; j < factIndex.length; j++ )
			{
				// make sure that any false rows don't get rewritten
				if ( formulaResult[ i ] )
				{
					// if the query is false, the whole row is false
					if ( ! grid[ i ][ queryIndex ] )
					{
						formulaResult[ i ] = false;
						
						queryResult[ i ] = false;
						
						break;
					}
					else
					{
						queryResult[ i ] = true;
					}
					
					formulaResult[ i ] = grid[ i ][  factIndex [ j ]  ];
				}
				else
				{
					break;
				}
			}
		}

		// now check the state of each literal for every row
		for ( int i = 0; i < rowNums; i++ )
		{
			// make sure that any false rows don't get rewritten
			if ( formulaResult[ i ] )
			{
				for ( int j = 0; j < literalIndex.length; j++ )
				{
					if( clauses.get(j).literalCount() == 2 )
					{
						if ( ( grid[ i ][  literalIndex[ j ][ 0 ]  ] == true ) && 
								( grid[ i ][  literalIndex[ j ][ 1 ]  ] == true ) && 
								( grid[ i ][  entailed[ j ]  ] == false ) )
						{
							formulaResult[ i ] = false;
						}
					}
					else
					{
						if ( ( grid[ i ][  literalIndex[ j ][ 0 ]  ] == true ) && 
								( grid[ i ][  entailed[ j ]  ] == false ) )
						{
							formulaResult[ i ] = false;
						}
					}
				}
			}
		}
		
		// count the number of rows that are true
		for ( int i = 0; i < rowNums; i++ )
		{
			if ( formulaResult[ i ] )
			{
				count++;
			}
			
			// the only way the search can return false is if
			// KB ⊨ α if and only if (KB ^ ¬α) is unsatisfiable
			if ( queryResult[ i ] == false && formulaResult[ i ] == true )
			{
				return false;
			}
		}
		
		// else return true
		return true;
	}

	public void getVariables( )
	{
		// add every literal from every horn clause into the list of variables
		for ( int i = 0; i < clauses.size( ); i++ )
		{
			for ( int j = 0; j < clauses.get( i ).literalCount( ); j++ )
			{
				// add literals from left of the entailment
				variables.add( clauses.get( i ).getLiteralsAtIndex( j ) );
			}
			// add literals from right of the entailment
			variables.add( clauses.get( i ).getEntailedLiteral( ) );
		}
		// Credit to jonathan-stafford's answer at 
		// stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
		// for an example of how to use a hash-set to cleanse an array-list of duplicate values
		Set<String> hs = new HashSet<>();
		hs.addAll(variables);
		variables.clear();
		variables.addAll(hs);
		
	}
	
	public void PopulateGrid( )
	{
		// Credit to Dhass's answer at careercup.com/question?id=17632666
		// for an example of how to populate a truth table grid
		for ( int i = 0; i < rowNums; i++ )
		{
			for ( int j = 0; j < colNums; j++ )
			{
				int v = i & 1 << colNums - 1 - j;

				grid[ i ][ j ] = ( v == 0 ? true : false );
			}
		}
	}

	public void GetColumnIndexOfFacts( )
	{
		for ( int i = 0; i < facts.size( ); i++ )
		{
			for ( int j = 0; j < variables.size( ); j++ )
			{
				if ( facts.get( i ).equals( variables.get( j ) ) )
				{
					factIndex[ i ] = j;
				}
				
				// get the index of the query
				if ( query.equals( variables.get( j ) ) )
				{
					queryIndex = j;
				}
				
			}
		}
	}

	public void GetColumnIndexOfLiterals( )
	{
		for ( int i = 0; i < variables.size( ); i++ )
		{
			for ( int j = 0; j < clauses.size( ); j++ )
			{
				for ( int k = 0; k < clauses.get( j ).literalCount( ); k++ )
				{
					if ( clauses.get( j ).getLiteralsAtIndex( k ).equals( variables.get( i ) ) )
					{
						literalIndex[ j ][ k ] = i;
					}
				}
				
				if ( clauses.get( j ).getEntailedLiteral( ).equals( variables.get( i ) ) )
				{
					entailed[ j ] = i;
				}
			}
		}
	}
}