import java.util.ArrayList;
import java.util.Random;

public class BanditManchot{
    
    public static void main(String[] args){
        ArrayList<Rouleau> rouleaux = new ArrayList<Rouleau>();
        int N = 5;
        Random rand = new Random();
        for(int i=0; i<N; i++){
            int numDepart = rand.nextInt(9 + 1);
            rouleaux.add(new Rouleau(i,numDepart));
        }
        Afficheur affiche = new Afficheur(rouleaux);

        try{
            System.in.read();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        for(int i=0; i<N; i++){
            rouleaux.get(i).stop();
            try{
                Thread.sleep(1000);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        affiche.stop();

        int num = rouleaux.get(0).getNumber();
        String result = new String("Vous avez gagne");
        for(int i=1; i<N; i++){
            if(num != rouleaux.get(i).getNumber()){
                result = new String("Desole vous avez perdu\nRententez votre chance une prochaine fois");
            }
        }
        System.out.println(result);
    }
}