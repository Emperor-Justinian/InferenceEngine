/**
 * 
 */
package inferenceEngine;

/**
 * @author Andrew Piubellini
 *
 */
public abstract class Algorithm {
  private String code;
  private String longName;
  private KnowledgeBase kb;
  private String toAsk;
  
  /**
   * Constructs a new Algorithm.
   * The KnowledgeBase and ASK query must be assigned later
   * using Algorithm.setKnowledgeBase and Algorithm.setAskStatement
   * before Algorithm.testAskStatement is called.
   */
  public Algorithm() {
    
  }
  
  /**
   * Constructs a new Algorithm, with a KnowledgeBase and ASK query preassigned.
   * 
   * @param aKb The KnowledgeBase to assign to the Algorithm.
   * @param aToAsk A String to be assigned as the Algorithm's ASK query.
   */
  public Algorithm(KnowledgeBase aKb, String aToAsk) {
    kb = aKb;
    toAsk = aToAsk;
  }
  
  /**
   * Returns the code required to invoke this Algorithm from the command line.
   * 
   * @return The code required to invoke this Algorithm from the command line.
   */
  public String getCode()
  {
    return code;
  }
  
  /**
   * Assigns the code used to invoke this Algorithm from the command line.
   * 
   * @param aCode The code to be used for invoking this Algorithm from the command line.
   */
  protected void setCode(String aCode)
  {
    code = aCode;
  }
  
  /**
   * Returns the descriptive name of the Algorithm.
   * 
   * @return The descriptive name of the Algorithm.
   */
  public String getLongName()
  {
    return longName;
  }
  
  /**
   * Assigns the descriptive name of the Algorithm.
   * 
   * @param aLongName The new descriptive name of the Algorithm.
   */
  protected void setLongName(String aLongName)
  {
    longName = aLongName;
  }
  
  /**
   * Returns the KnowledgeBase assigned to the Algorithm via Algorithm.setKnowledgeBase.
   * 
   * @return The KnowledgeBase assigned to the Algorithm via Algorithm.setKnowledgeBase.
   */
  protected KnowledgeBase getKnowledgeBase()
  {
    return kb;
  }
  
  /**
   * Assigns a given KnowledgeBase to the Algorithm.
   * 
   * @param aKb The KnowledgeBase to assign to the Algorithm.
   */
  public void setKnowledgeBase(KnowledgeBase aKb)
  {
    kb = aKb;
  }
  
  /**
   * Returns the literal assigned to the Algorithm via Algorithm.setAskStatement.
   * 
   * @return The literal assigned to the Algorithm via Algorithm.setAskStatement.
   */
  protected String getToAsk()
  {
    return toAsk;
  }
  
  /**
   * Assigns the literal that the Algorithm should query its KnowledgeBase about
   * when Algorithm.testAskStatement is called.
   * 
   * @param aToAsk A String to be assigned as the Algorithm's ASK query.
   */
  public void setAskStatement(String aToAsk)
  {
    toAsk = aToAsk;
  }
  
  /**
   * Checks whether the query (assigned using Algorithm.setAskStatement)
   * can be entailed from the KnowledgeBase (assigned using Algorithm.setKnowledgeBase).
   * 
   * @return A String representing the result of the query.
   */
  abstract public String testAskStatement();
  
  abstract public boolean CheckFacts();
}