import java.util.Random;

public class Rouleau{
    private int number;
    private Thread th;
    private boolean active;
    private Random rand;
    public Rouleau(int numeroRouleau, int numDepart){
        rand = new Random();
        this.setNumber(numDepart);
        this.active = true;
        this.th = new Thread( new Runnable(){
            public void run(){
                while(getActive()){
                    changeNumber();
                    int aleatoire = (rand.nextInt(400));
                    try{
                        Thread.sleep(100 + aleatoire);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.th.start();
    }

    public synchronized void changeNumber(){
        this.number = (this.getNumber()+1)%10;
    } 

    public synchronized Boolean getActive(){
        return this.active;
    }

    public synchronized void stop(){
        this.active = false;
    }

    public int getNumber(){
        return this.number;
    }

    public void setNumber(int num){
        this.number = num;
    }

    public Thread getThread(){
        return this.th;
    }
}