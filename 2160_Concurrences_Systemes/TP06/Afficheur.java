import java.util.ArrayList;

public class Afficheur{
    private ArrayList<Rouleau> rouleaux;
    private Thread th;
    private Boolean active;

    public Afficheur(ArrayList<Rouleau> list){
        this.rouleaux = list;
        this.active = true;
        this.th = new Thread( new Runnable(){
            public void run(){
                while(getActive()){
                    affichage();
                    try{
                        Thread.sleep(250);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.th.start();
    }


    public synchronized void affichage(){
        for(int i=0 ; i<this.rouleaux.size(); i++){
            System.out.print(this.rouleaux.get(i).getNumber() + " ");
        }
        System.out.print("\n");
    } 

    public synchronized Boolean getActive(){
        return this.active;
    }

    public synchronized void stop(){
        this.active = false;
    }
}