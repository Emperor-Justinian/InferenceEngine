/**
 * 
 */
package inferenceEngine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author andrew
 *
 */
public class Main {

  /**
   * 
   */
  public Main() {
    // TODO Auto-generated constructor stub
  }

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
      
      kb.printSentenceStrings();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

}
