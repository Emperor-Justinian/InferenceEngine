/**
 * 
 */
package inferenceEngine;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author andrew
 *
 */
public class KnowledgeBase {
  String[] sentenceStrings;
  
  /**
   * 
   */
  public KnowledgeBase() {
    // TODO Auto-generated constructor stub
  }
  
  public KnowledgeBase(BufferedReader reader) {
    readInput(reader);
  }
  
  public BufferedReader readInput(BufferedReader reader) {
    try {
      // The first line should contain "TELL" and contains no useful information, so disregard it.
      reader.readLine();
      
      // The second line contains the sentences to be added to the knowledge base.
      String tellString = reader.readLine();
      
      /*
       * Remove whitespace characters from tellString.
       * 
       * Credit to Kevin Bowersox's answer at
       * http://stackoverflow.com/questions/15633228/how-to-remove-all-white-spaces-in-java
       * for an example on how to do this using a regular expression.
       */
      tellString = tellString.replaceAll("\\s", "");
      
      sentenceStrings = tellString.split(";");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(2);
    }
    
    return reader;
  }
  
  public void printSentenceStrings() {
    int sentences = sentenceStrings.length;
    
    System.out.println("Printing " + String.valueOf(sentences) + " sentences...");;
    
    for (int i = 0; i < sentences; i++) {
      System.out.println(sentenceStrings[i]);
    }
    
    System.out.println("Printed " + String.valueOf(sentences) + " sentences.");
  }

}
