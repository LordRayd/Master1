import java.util.ArrayList;

public class SynchroLecteurRedacteurWaitNotifyPrioRedacteur extends SynchroLecteurRedacteur{

    private ArrayList<Thread> demandeLecteur, demandeRedacteur;
    private int nbLecteur =0;
    private int nbRedacteur = 0;

    public SynchroLecteurRedacteurWaitNotifyPrioRedacteur(){
        this.demandeLecteur = new ArrayList<Thread>();
        this.demandeRedacteur = new ArrayList<Thread>();
    }
    
	public void debutLire(){
        synchronized(this){
            if(this.nbRedacteur != 0 || !demandeLecteur.isEmpty()){
                demandeLecteur.add(Thread.currentThread());
                synchronized(Thread.currentThread()){
                    try{
                        Thread.currentThread().wait();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                demandeLecteur.remove(Thread.currentThread());
            }
            this.nbLecteur++;
        }
	}

	public void finLire(){
        synchronized(this){
            this.nbLecteur--;
            if(this.nbLecteur==0 && !demandeRedacteur.isEmpty()){
                synchronized(demandeRedacteur.get(0)){
                    demandeRedacteur.get(0).notify();
                }
            }
        }
	}

	public void debutEcrire(){
        synchronized(this){
            if(this.nbLecteur != 0 || this.nbRedacteur !=0 || !demandeRedacteur.isEmpty()){
                this.demandeRedacteur.add(Thread.currentThread());
                synchronized(Thread.currentThread()){
                    try {
                        Thread.currentThread();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                }
                this.demandeRedacteur.remove(Thread.currentThread());
            }
            this.nbRedacteur++;
        }
    }
    
	public void finEcrire(){
        synchronized(this){
            this.nbRedacteur--;
            if(!this.demandeRedacteur.isEmpty()){
                synchronized(this.demandeRedacteur.get(0)){
                    this.demandeRedacteur.get(0).notify();
                }
            }else{
                for(Thread th : this.demandeLecteur){
                    synchronized(th){
                        th.notify();
                    }
                }
            }
        }
	}
}