import java.util.ArrayList;

public class SynchroLecteurRedacteurWaitNotify extends SynchroLecteurRedacteur{

    private int mutex = 1;
    private ArrayList<Thread> lecteur, redacteur;

    public SynchroLecteurRedacteurWaitNotify(){
        this.lecteur = new ArrayList<Thread>();
        this.redacteur = new ArrayList<Thread>();
    }
    
	public void debutLire(){
        int val = 0;
        synchronized(this){
            val = this.mutex;
            if(val != 0){
                this.mutex = 0;
            }else{
                lecteur.add(Thread.currentThread());
                try{
                    Thread.currentThread().wait();
                }catch(Exception e){
                }
            }
        }
	}

	public void finLire(){
        synchronized(this){
            Thread reveille = null;
            if(!this.redacteur.isEmpty()){
                reveille = this.redacteur.get(0);
                this.redacteur.remove(0);
            }else if(!this.lecteur.isEmpty()){
                reveille = this.lecteur.get(0);
                this.lecteur.remove(0);
            }
            if(reveille != null){
                synchronized(reveille){
                    try{
                        reveille.notify();
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
            }else{
                this.mutex = 1;
            }
        }
	}

	public void debutEcrire(){
        int val = 0;
        synchronized(this){
            val = this.mutex;
            if(val != 0){
                this.mutex = 0;
            }else{
                this.redacteur.add(Thread.currentThread());
                try{
                    Thread.currentThread().wait();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    
	public void finEcrire(){
        synchronized(this){
            Thread reveille = null;
        if(!this.redacteur.isEmpty()){
            reveille = this.redacteur.get(0);
            this.redacteur.remove(0);
        }else if(!this.lecteur.isEmpty()){
            reveille = this.lecteur.get(0);
            this.lecteur.remove(0);
        }
        if(reveille != null){
            synchronized(reveille){
                try{
                    reveille.notify();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }else{
            
            this.mutex = 1;
        }
        }
        
	}
}