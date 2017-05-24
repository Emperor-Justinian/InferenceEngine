/**
 * 
 */
package inferenceEngine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author andrew
 *
 */
public class Main {
  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      /*
       * Note that args[0] is the algorithm to use, and args[1] is the path to the input file.
       * 
       * Credit to https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
       * for an example on how to work with FileReaders and BufferedReaders.
       */
      BufferedReader inputFile = new BufferedReader(new FileReader(args[1]));
      
      KnowledgeBase kb = new KnowledgeBase(inputFile);
      
      System.out.println(kb);
      
      /*
       * While kb was being constructed above, it should have read the first 2 lines of inputFile.
       * The third line should contain the string "ASK", which we can discard.
       */
      inputFile.readLine();
      
      // The fourth line of inputFile should contain the literal to test; store it in a variable.
      String askStatement = inputFile.readLine();
      
      // This array should contain an instance of each subclass of Algorithm
      Algorithm[] algorithms = {
    		  new ForwardChaining(kb, askStatement),
    		  new BackwardChaining(kb, askStatement),
          new TruthTable(kb, askStatement)
      };
      
      // Initialise loop conditions
      int currentIndex = 0;
      boolean searchingForAlgorithm = true;
      
      do {
        if (algorithms[currentIndex].getCode().equals(args[0])) {
          // We found the correct algorithm, so the search is over
          searchingForAlgorithm = false;
          
          System.out.println("Result of " + algorithms[currentIndex].getLongName() + " search:");
          System.out.println(algorithms[currentIndex].testAskStatement());
        }
        
        currentIndex++;
      } while (currentIndex < algorithms.length && searchingForAlgorithm);
    } catch (FileNotFoundException e) {
    	
      e.printStackTrace();
      System.exit(1);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(2);
    }
  }

}
