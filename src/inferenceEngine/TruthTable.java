/**
 * 
 */
package inferenceEngine;

/**
 * @author andrew
 *
 */
public class TruthTable extends Algorithm {

  /**
   * 
   */
  public TruthTable() {
    setCode("TT");
  }

  /**
   * @param aKb
   * @param aToAsk
   */
  public TruthTable(KnowledgeBase aKb, String aToAsk) {
    super(aKb, aToAsk);
    
    setCode("TT");
  }
  
  private boolean testClause(HornClause clause) {
    
    
    return false; //TODO
  }
  
  /* (non-Javadoc)
   * @see inferenceEngine.Algorithm#testAskStatement()
   */
  @Override
  public boolean testAskStatement() {
    /*
    // Initialise loop conditions
    boolean result = true;
    int currentIndex = 0;
    
    do {
      result = testClause(getKnowledgeBase().getClauses().get(currentIndex));
      currentIndex++;
    } while (result && currentIndex < getKnowledgeBase().getClauses().size());
     */
    
    // Stub return value, to be replaced once implementation of this method is complete
    return false; // TODO
  }

}
