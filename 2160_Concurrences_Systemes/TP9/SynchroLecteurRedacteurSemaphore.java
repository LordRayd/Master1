import java.util.concurrent.Semaphore;

public class SynchroLecteurRedacteurSemaphore extends SynchroLecteurRedacteur{
    
    private Semaphore sMutex;

    public SynchroLecteurRedacteurSemaphore(){
        this.sMutex = new Semaphore(1);
	}
	public void debutLire(){
        this.sMutex.acquireUninterruptibly();
	}

	public void finLire(){
        this.sMutex.release();
	}

	public void debutEcrire(){
        this.sMutex.acquireUninterruptibly();
	}
	public void finEcrire(){
        this.sMutex.release();
	}
}