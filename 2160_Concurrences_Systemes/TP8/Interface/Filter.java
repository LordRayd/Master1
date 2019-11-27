package messagebox;

/**
 * class Filter
 * pour filtrer les message d'une MessageBox
 */
/**
   *   class Filter 
   *  pour filtere les Message d'une  MessageBox
   */
public class Filter{
 
  private Thread sender;
  /**
   * contruire un filtre vide
   *  le plus viel objet de la MessageBox
   */
  public Filter(){
    this.sender = null;
  }

  /**
   *  le plus viel objet de la MessageBox 
   *         venant d'un thread particulier
   *@param emt thread emetteur du message
   **/
  public  Filter(Thread emt_){
    this.sender = emt_;
  }

  public Thread getSender(){
    return this.sender;
  }


  /**
   * applique le filtre sur un message
   * @param mes le message a tester 
   * @return true si le message correspond au filtre courant  
   */
  public boolean isGood(Message mes){
    boolean result=true;
    if(this.sender != mes.getSender()){
      result = false;
    }
	  return result;
  }
}
