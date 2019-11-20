public class TestBarriere{
    public static void main(String args[]){
        final int nbT=3;
        Barrier unBar= new Barrier(nbT);
        
        for(int i=0; i < nbT;i++){
            Test th = new Test(unBar,String.valueOf(i));
            th.start();
        }
    }
}