class  T2 extends Thread {
    SynchroL unLock;
    public T2(SynchroL l){
        this.unLock = l;
    }
    public void run(){
        //  Etape1;
        System.out.println("Avant ok");  
        unLock.ok(); 
        // previent de la fin de t1
        System.out.println("Apres ok");
        //...
    }
}