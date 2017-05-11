import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.util.*;

class Main
{
	
	public static final int METHOD_COUNT = 3;
	public static HornClause gHornClause;
	public static logicMethod[] lMethods;
	
	public static void main(String[] args)
	{
		//Create method objects
		InitMethods();
		
		//  [0] - method name
		//  [1] - filename containing puzzle(s)
		
		if (args.length < 2) {
			System.out.println("Usage: iengine <method> <filename>.");
			System.exit(1);			
		}
		
		//Get the horn clauses from the file
		gHornClause = readProblemFile(args[1]);
		
		String method = args[1];
		logicMethod thisMethod = null;
		
		//determine which method the user wants to use to solve the puzzles
		for(int i = 0; i < METHOD_COUNT; i++)
		{
			//do they want this one?
			if(lMethods[i].code.compareTo(method) == 0)
			{
				//yes, use this method.
				thisMethod = lMethods[i];
			}
		}
		
		//Has the method been implemented?
		if(thisMethod == null)
		{
			//No, give an error
			System.out.println("Search method identified by " + method + " not implemented. Methods are case sensitive.");
			System.exit(1);
		}
		
		//Print information about this solution
		System.out.println(args[0] + "   " + method + "   " + thisMethod.Searched.size());
		System.exit(0);
	}
	
	private static void InitMethods()
	{
		lMethods = new logicMethod[METHOD_COUNT];
		lMethods[0] = new TruthTable();
		lMethods[1] = new ForwardChaining();
		lMethods[2] = new BackwardChaining();
	}
	
	private static HornClause readProblemFile(String fileName)
	{
		try
		{
			//create file reading objects
			FileReader reader = new FileReader(fileName);
			BufferedReader textFile = new BufferedReader(reader);
			HornClause result;
			
			String line = textFile.readLine();
			
			if (line == "TELL")
			{
				line = textFile.readLine();
				String[] characters = line.split("");
				LinkedList<String> purgedCharacters = new LinkedList<String>();
				for(int i = 0; i < characters.length; i++)
				{
					if (characters[i] != " " | characters[i] != ">" | characters[i-1] != "<")
					{
						purgedCharacters.add(characters[i]);
					}
				}
			} else
			{
				System.out.println("Error: File not formatted correctly. ie");
				System.out.println("TELL");
				System.out.println("Horn clauses separated by semicolons");
				System.out.println("ASK");
				System.out.println("a proposition symbol");
				System.exit(1);
			}
			
		}
		catch(FileNotFoundException ex)
		{
			//The file didn't exist, show an error
			System.out.println("Error: File \"" + fileName + "\" not found.");
			System.out.println("Please check the path to the file.");
			System.exit(1);
		}
		catch(IOException ex)
		{
			//There was an IO error, show and error message
			System.out.println("Error in reading \"" + fileName + "\". Try closing it and programs that may be accessing it.");
			System.out.println("If you're accessing this file over a network, try making a local copy.");
			System.exit(1);
		}
		
		//this code should be unreachable. This statement is simply to satisfy Eclipse.
		return null;
	}
}