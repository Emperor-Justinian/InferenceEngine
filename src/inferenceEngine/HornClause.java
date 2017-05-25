/**
 * 
 */
package inferenceEngine;

import java.util.LinkedList;

/**
 * @author Andrew Piubellini
 *
 */
public class HornClause {
  private LinkedList<String> literals;
  private String entailedLiteral;
  
  /**
   * Constructs a new HornClause, based on the String provided in the sentence parameter.
   * 
   * @param sentence A String representing the new HornClause.
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
  
  /**
   * Returns a LinkedList of String, containing the literals that the HornClause refers to
   * (excluding the entailed literal).
   * 
   * @return A LinkedList of String, containing the literals that the HornClause refers to.
   */
  public LinkedList<String> getLiterals() {
    return literals;
  }
  
  /**
   * Returns the literal (represented as a String) at a given index.
   * 
   * @param index The index from which to get the literal.
   * @return The literal (represented as a String).
   */
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
  
  /**
   * Returns the number of literals that the HornClause refers to (excluding the entailed literal).
   * 
   * @return The number of literals that the HornClause refers to (excluding the entailed literal).
   */
  public int literalCount() {
    return literals.size();
  }
  
  /**
   * Checks if the HornClause refers to a given literal. If so, removes that reference.
   * 
   * <p>Note that this method is not capable of removing multiple instances of the same literal.
   * As such, you should not add multiple instances of the same literal to a HornClause.
   * 
   * @param literal The literal to check for and remove.
   */
  public void removeLiteral(String literal) {
    literals.remove(literal);
  }
  
  /**
   * Returns the literal (represented as a String) that the HornClause entails.
   * 
   * @return The literal (represented as a String) that the HornClause entails.
   */
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
