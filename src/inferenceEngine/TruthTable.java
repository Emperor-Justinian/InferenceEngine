package inferenceEngine;

import java.util.ArrayList;

public class TruthTable extends Algorithm {
	/**
	 * @author Tim
	 *
	 */
	
	private ArrayList<HornClause> clauses;
	private ArrayList<String> facts;
	private String query;

	private ArrayList<String> variables;
	private int colNums;
	private int rowNums;
	private boolean[][] grid;
	private boolean[] formulaColumn;
	private int[][] literalIndex;
	private int[] factIndex;
	private int[] entailed;
	private int count;
	
	public TruthTable()
	{
	    setCode("TT");
	}
	
	public TruthTable(KnowledgeBase aKb, String aToAsk) {
    super(aKb, aToAsk);
    
	    clauses = aKb.getClauses();
		facts = aKb.getFacts();
		query = aToAsk;
		
		variables = new ArrayList<String>();
		getVariables();

		// one column for every literal
		colNums = variables.size();

		// 2 to the power of n rows equals the number of variables squared
		rowNums = ( ( int ) Math.pow( 2, ( variables.size() ) ) );

		// stores the boolean value of each variable in a row
		// each row contains an array of boolean values for each variable
		grid = new boolean[ rowNums ][ colNums ];

		// a final column that determines the result of each row
		formulaColumn = new boolean[ rowNums ];

		literalIndex = new int [ clauses.size() ][ 2 ];

		factIndex = new int [ facts.size() ];

		entailed = new int[ clauses.size() ];

		count = 0;

		PopulateGrid();

		GetColumnIndexOfFacts();

		NumberOfLiteralsInClause();

	    setCode("TT");
	}
	
	@Override
	public String testAskStatement()
	{
		String output = "";
		
		// CheckFacts check's whether the query can be proven 
		if ( CheckFacts() )
		{
			// if so, output YES:
			output = "YES " + count;
		}
		
		else
		{
			// else output "(Query) could not be proven"
			output = query + " could not be proven.";
		}
		
		return output;			
	}

	@Override
	public boolean CheckFacts()
	{
		for ( int i = 0; i < rowNums; i++ )
		{
			for ( int j = 0; j < literalIndex.length; j++ )
			{
				formulaColumn[i] = grid[i][factIndex[j]];
			}
		}

		for ( int i = 0; i < rowNums; i++ )
		{
			if ( formulaColumn[i] )
			{
				for ( int j = 0; j < literalIndex.length; j++ )
				{
					if ( literalIndex[j].length == 1 )
					{
						if ( ( grid[i][ literalIndex[j][0] ] == true ) && 
								( grid[i][ entailed[j] ] == false) )
						{
							formulaColumn[i] = false;
						}
					}
					else
					{
						if ( ( grid[i][ literalIndex[j][0] ] == true ) && 
								( grid[i][literalIndex[j][1]] == true ) && 
								( grid[i][entailed[j]] == false) )
						{
							formulaColumn[i] = false;
						}
					}
				}
			}
		}

		for ( int i = 0; i < formulaColumn.length; i++ )
		{
			if(formulaColumn[i])
			{
				count++;
			}
		}

		if (count != 0)
		{
			return true;
		}

		return false;
	}

	public void getVariables()
	{
		// add every literal from every horn clause into the list of variables
		for ( int i = 0; i < clauses.size(); i++ )
		{
			for ( int j = 0; j < clauses.get(i).literalCount(); j++ )
			{
				// make sure each literal is only included once
				if ( variables.equals( clauses.get(i).getLiteralsAtIndex(j) ) )
				{	
					// do nothing
				}
				else
				{
					// add literals from left of the entailment
					variables.add( clauses.get(i).getLiteralsAtIndex(j) );
				}
			}
			if ( variables.equals( clauses.get(i).getEntailedLiteral() ) )
			{	
				// do nothing
			}
			else
			{
				// add literals from right of the entailment
				variables.add( clauses.get(i).getEntailedLiteral() );
			}
		}
	}

	public void PopulateGrid()
	{
		// reference: https://www.careercup.com/question?id=17632666

		for (int i = 0; i < rowNums; i++)
		{
			for (int j = 0; j < colNums; j++)
			{
				int v = i & 1 << colNums - 1 - j;

				grid[i][j] = (v == 0 ? true : false);
			}
		}
	}

	public void GetColumnIndexOfFacts()
	{
		for ( int i = 0; i < facts.size(); i++ )
		{
			for ( int j = 0; j < variables.size(); j++ )
			{
				if ( facts.get(i).equals( variables.get(j) ) )
				{
					factIndex[i] = j;
				}
			}
		}
	}

	public void NumberOfLiteralsInClause()
	{
		for ( int i = 0; i < clauses.size(); i++ )
		{
			for ( int j = 0; j < clauses.get(i).literalCount(); j++ )
			{
				for ( int k = 0; k < variables.size(); k++ )
				{
					if ( clauses.get(i).getLiteralsAtIndex(j).equals( variables.get(k) ) )
					{
						literalIndex[i][j] = k;
					}

					if ( clauses.get(i).getEntailedLiteral().equals( variables.get(k) ) )
					{
						entailed[i] = k;
					}
				}
			}
		}
	}
}






