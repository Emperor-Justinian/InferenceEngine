/**
 * 
 */
package inferenceEngine;

/**
 * @author andrew
 *
 */
public abstract class Algorithm {
  private String code;
  private KnowledgeBase kb;
  private String toAsk;
  
  /**
   * 
   */
  public Algorithm() {
    // TODO Auto-generated constructor stub
  }
  
  public Algorithm(KnowledgeBase aKb, String aToAsk) {
    kb = aKb;
    toAsk = aToAsk;
  }
  
  public String getCode() {
    return code;
  }
  
  protected void setCode(String aCode) {
    code = aCode;
  }
  
  protected KnowledgeBase getKnowledgeBase() {
    return kb;
  }
  
  public void setKnowledgeBase(KnowledgeBase aKb) {
    kb = aKb;
  }
  
  protected String getToAsk() {
    return toAsk;
  }
  
  public void setAskStatement(String aToAsk) {
    toAsk = aToAsk;
  }
  
  abstract public boolean testAskStatement();

}
