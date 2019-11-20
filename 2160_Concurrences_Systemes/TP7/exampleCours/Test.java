public class Test {
    public static void main(String args[]) {   
        SynchroL sync = new SynchroL();
        T1 t1 = new T1(sync);
        T1 t1bis = new T1(sync);
        T2 t2 = new T2(sync);
        t1.start();
        t1bis.start();
        t2.start();
        //...
    }
}