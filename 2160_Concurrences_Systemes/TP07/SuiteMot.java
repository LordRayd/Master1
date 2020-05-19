import java.util.ArrayList;

public class SuiteMot extends Thread{
    private SuiteMot nextThread;
    private String mot;
    public Object lock;

    public SuiteMot(String mot_, Object lock_){
        this.mot = mot_;
        this.lock = lock_;
    }

    private void setNextThread(SuiteMot th_ ){
        this.nextThread = th_;
    }

    public void run() {

        try {
            this.wait();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        while(true){
            synchronized(this.nextThread){
                System.out.println(this.mot);
                try {
                    Thread.sleep(1000);
                    this.nextThread.notify();
                } catch (Exception e) {
                    //e.printStackTrace();
                }
                try {
                    this.wait();
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
            
        }
    }

    public static void main(String args[]){
        if(args.length >= 0 ) {
            Object object = new Object();
            ArrayList<SuiteMot> listMot = new ArrayList<SuiteMot>();
            for(int i=0; i < args.length;i++){
                listMot.add(new SuiteMot(args[i],object));
            }
            for(int i=0; i< listMot.size()-1; i++){
                listMot.get(i).setNextThread(listMot.get(i+1));
            }
            listMot.get(listMot.size()-1).setNextThread(listMot.get(0));
            /*
            System.out.println(listMot.get(2).nextThread.mot);
            System.out.println(listMot.get(0).nextThread.mot);
            System.out.println(listMot.get(1).nextThread.mot);
            */
            
            for(Thread th : listMot){
                th.start();
            }
            try {
                synchronized(listMot.get(0)){
                    listMot.get(0).notify();
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
            //listMot.get(0).notify();
        }
    }
}