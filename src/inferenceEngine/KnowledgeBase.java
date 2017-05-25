/**
 * 
 */
package inferenceEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Andrew Piubellini
 *
 */
public class KnowledgeBase {
  private ArrayList<HornClause> clauses;
  private ArrayList<String> facts;
  
  /**
   * Constructs an empty KnowledgeBase.
   */
  public KnowledgeBase() {
    clauses = new ArrayList<HornClause>();
    facts = new ArrayList<String>();
  }
  
  /**
   * Constructs a new KnowledgeBase, then calls this.readInput on the reader parameter.
   * 
   * @param reader A BufferedReader containing a TELL statement.
   */
  public KnowledgeBase(BufferedReader reader) {
    clauses = new ArrayList<HornClause>();
    facts = new ArrayList<String>();
    
    readInput(reader);
  }
  
  /**
   * Reads a TELL statement into the KnowledgeBase.
   * 
   * <p>Note that this method will read 2 lines from the
   * BufferedReader provided by the reader parameter.
   * As a result, this method will modify the BufferedReader that reader points to.
   * 
   * <p>The first line of input is assumed to contain the string "TELL" and is ignored.
   * The second line of input is assumed to contain the relevant Horn clauses and facts.
   * 
   * <p>This method removes whitespace from the second line of input,
   * but otherwise does not perform input sanitisation.
   * 
   * @param reader A BufferedReader containing a TELL statement.
   */
  public void readInput(BufferedReader reader) {
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
  }
  
  /**
   * Returns an ArrayList of the HornClauses stored in the KnowledgeBase.
   * 
   * @return An ArrayList of the HornClauses stored in the KnowledgeBase.
   */
  public ArrayList<HornClause> getClauses() {
    return clauses;
  }
  
  /**
   * Returns an ArrayList of facts (represented as Strings) stored in the KnowledgeBase.
   * 
   * @return An ArrayList of String, containing the facts stored in the KnowledgeBase.
   */
  public ArrayList<String> getFacts() {
    return facts;
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
