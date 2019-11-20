import java.util.ArrayList;

public class Barrier {
    private int nb;
    private int currentThread;
    private boolean isLock;

    public Barrier(int nbThreads){
        this.nb = nbThreads;
        this.currentThread = 1;
    }

    public synchronized void synchr(){
        if(this.currentThread == this.nb){
            this.currentThread = 1;
            this.notifyAll();
        } else {
            this.currentThread++;
            try {
                this.wait();
            } catch (Exception e) {
                //TODO: handle exception
            }

        }
    }
}