/**
 * 
 */
package inferenceEngine;

/**
 * @author andrew / tim
 *
 */
public abstract class Algorithm {
  private String code;
  private KnowledgeBase kb;
  private String toAsk;
  
  public Algorithm(KnowledgeBase aKb, String aToAsk) {
    kb = aKb;
    toAsk = aToAsk;
  }
  
  public String Solve()
  {

  }

  public bool CheckFacts()
  {
    
  }
}