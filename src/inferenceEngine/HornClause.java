/**
 * 
 */
package inferenceEngine;

import java.util.LinkedList;

/**
 * @author andrew
 *
 */
public class HornClause {
  private LinkedList<String> literals;
  private String entailedLiteral;
  
  /**
   * 
   */
  public HornClause(String sentence) {
    literals = new LinkedList<String>();
    
    String[] splitByEntailment = sentence.split("=>");
    
    String[] splitByAnd = splitByEntailment[0].split("&");
    
    for (int i = 0; i < splitByAnd.length; i++) {
      literals.addLast(splitByAnd[i]);
    }
    
    entailedLiteral = splitByEntailment[1];
  }
  
  public LinkedList<String> getLiterals() {
    return literals;
  }
  
  public String getLiteralsAtIndex(int index) {
    try {
      if (index >= 0 && index < literals.size()) {
        return literals.get(index);
      } else {
        throw new IndexOutOfBoundsException();
      }
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
      System.exit(3);
      
      // This code should be unreachable, but is required to satisfy Java's compiler
      return null;
    }
  }
  
  public int literalCount() {
    return literals.size();
  }
  
  public void removeLiteral(String literal) {
    literals.remove(literal);
  }
  
  public String getEntailedLiteral() {
    return entailedLiteral;
  }
  
  @Override
  public String toString() {
    String returnString = "";
    
    for (int i = 0; i < literals.size(); i++) {
      if (i != 0) {
        returnString += "^";
      }
      
      returnString += literals.get(i);
    }
    
    returnString += "=>";
    
    returnString += entailedLiteral;
    
    return returnString;
  }

}
