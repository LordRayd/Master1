class SynchroL {
    Object lock; 
    // un Objet pour sont verrrou  (ou l’ojbect this)
    boolean isLock; //  le verrou pris ou pas
    public SynchroL() { // contructeur
        lock = new Object();
        isLock = true;
    }
    public void testSynchro(){  
        // bloquante
        synchronized (lock){
            if (isLock) 
            //il faut le verrou de l'objet  pour le wait
            try {
                lock.wait(); 
                // libère aussi le verrou
            }  catch (InterruptedException e) {

            }
        }
    }
    public void ok(){
        synchronized(lock){ 
            // prend le verrou pour le  notify
            lock.notifyAll(); 
            // libère le wait à la fin de ce bloc synchronized
            isLock = false; 
        }
    }
} 