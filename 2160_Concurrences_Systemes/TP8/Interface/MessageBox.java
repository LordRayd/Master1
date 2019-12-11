package messagebox;

import java.util.ArrayList;
import java.util.HashMap;

import messagebox.Filter;
import messagebox.Message;

/**
 * class MessageBox boite aux lettres pour la communication de threads
 */
public class MessageBox {

  private ArrayList<Message> listMessage;
  private HashMap<Thread, Thread> waitingMessage;

  /**
   * constructeur
   **/
  public MessageBox() {
    this.listMessage = new ArrayList<Message>();
    this.waitingMessage = new HashMap<Thread, Thread>();
  }

  /**
   * Depose un message dans la boite aux lettres
   * 
   * @param mes le message a deposer
   **/
  public void deposit(Message mes_) {
    this.listMessage.add(mes_);
    Thread attent = this.waitingMessage.get(mes_.getSender());
    if ( attent != null) {
      synchronized (mes_.getSender()) {
        mes_.getSender().notify();
      }
    }
  }

  /**
   * extrait le plus viel objet dans la MessageBox correspondant a un fitre
   * 
   * @param f filtre a appliquer (eventuellement null)
   * @return le message extrait
   **/
  public Message receive(Filter f_) {
    Message result = tryReceive(f_);
    if (result == null) {

      synchronized (f_.getSender()) {
        this.waitingMessage.put(f_.getSender(), Thread.currentThread());
        try {
          f_.getSender().wait();
        } catch (Exception e) {
        }
      }
    }
    for (Message msg : this.listMessage) {
      if (f_.isGood(msg)) {
        result = msg;
        this.listMessage.remove(msg);
        return result;
      }
    }

    return result;
  }

  /**
   * test si la MessageBox contient un message qui repond a un fitre donne
   * 
   * @param f filtre a appliquer (eventuellement null)
   * @return Message en cas de succes (null sinon)
   **/
  public Message tryReceive(Filter f_) {
    Message result = null;
    for (Message msg : this.listMessage) {
      if (f_.isGood(msg)) {
        result = msg;
        return result;
      }
    }
    return result;
  }

}
