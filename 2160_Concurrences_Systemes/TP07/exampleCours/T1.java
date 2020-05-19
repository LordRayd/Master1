class  T1 extends Thread{
    SynchroL unLock;
    public T1(SynchroL loc){
        this.unLock = loc;
    }
    public void run(){
        System.out.println("Avant Synchro");
        unLock.testSynchro();
        System.out.println("Apres Synchro");
        //...
    }
}