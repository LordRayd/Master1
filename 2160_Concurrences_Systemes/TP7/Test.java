public class Test extends Thread {
    private Barrier bar;
    private String mot;
    public Test(Barrier bar,String mot){
        this.bar=bar;
        this.mot=mot;
    }
    public void run() {
        while(true){
            bar.synchr();
            System.out.println(mot);
        }
    }
}