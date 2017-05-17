/**
 * 
 */
package inferenceEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author andrew
 *
 */
public class KnowledgeBase {
  //String[] sentenceStrings;
  private ArrayList<HornClause> clauses;
  private ArrayList<String> facts;
  
  /**
   * 
   */
  public KnowledgeBase() {
    clauses = new ArrayList<HornClause>();
    facts = new ArrayList<String>();
  }
  
  public KnowledgeBase(BufferedReader reader) {
    clauses = new ArrayList<HornClause>();
    facts = new ArrayList<String>();
    
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
      
      String[] sentenceStrings = tellString.split(";");
      
      for (int i = 0; i < sentenceStrings.length; i++) {
        if (sentenceStrings[i].contains("=>")) {
          clauses.add(new HornClause(sentenceStrings[i]));
        } else {
          facts.add(sentenceStrings[i]);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(2);
    }
    
    return reader;
  }
  
  @Override
  public String toString() {
    String returnString = "";
    
    int count = clauses.size();
    
    /*
     * Credit to
     * http://stackoverflow.com/questions/207947/how-do-i-get-a-platform-dependent-new-line-character
     * for suggesting the use of System.lineSeparator().
     */
    
    returnString += "Printing " + String.valueOf(count) + " Horn clauses...";
    returnString += System.lineSeparator();
    
    for (int i = 0; i < count; i++) {
      returnString += clauses.get(i);
      returnString += System.lineSeparator();
    }
    
    returnString += "Printed " + String.valueOf(count) + " Horn clauses.";
    returnString += System.lineSeparator();
    
    count = facts.size();
    
    returnString += "Printing " + String.valueOf(count) + " facts...";
    returnString += System.lineSeparator();
    
    for (int i = 0; i < count; i++) {
      returnString += facts.get(i);
      returnString += System.lineSeparator();
    }
    
    returnString += "Printed " + String.valueOf(count) + " facts.";
    returnString += System.lineSeparator();
    
    return returnString;
  }

}
