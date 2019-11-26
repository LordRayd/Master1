import java.util.ArrayList;

public class Labyrinthe {
    private ArrayList<ArrayList<Integer>> labyrinthe;

    public Labyrinthe(ArryList<ArrayList<Integer>> laby_){
        this.labyrinthe = laby_;
    }

    public String toString(){
        String ret = new String;
        this.labyrinthe.stream().map( n -> n.stream().map( m -> 
            switch(m){
                case 0 :
                    ret += "\u001B[40m" + "  " + "\u001B[0m";
                case 1 :
                    ret += "\u001B[43m" + "  "+ "\u001B[0m" ;
                case 2 :
                    ret += "\u001B[44m" + "  "+ "\u001B[0m" ;
                case 9 :
                    ret += "\u001B[41m" + "  " + "\u001B[0m";
            } 
            //).mkString).mkString("\n" )
        return ret;
    }
}