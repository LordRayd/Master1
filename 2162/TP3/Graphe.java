import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Graphe{
    
    public Collection<Trajet> trajets;
    
    public Graphe(){
        this.trajets = new HashSet<Trajet>();
    }

    public Collection<Trajet> getTrajets(){
        return this.trajets;
    }

    public static boolean ajouteNoeud(Graphe graphe_ , Station depart_, Station arrive_, Ligne ligne_){
        return graphe_.getTrajets().add(new Trajet(ligne_, depart_, arrive_));
    }
    
    public static boolean stationEstUnSommet(Graphe graphe_, Station station_){
        return graphe_.getTrajets().stream().anyMatch((t) -> t.getDepart().equals(station_));
    }

    public static boolean estUnVoisinDe(Graphe graphe_, Station stationA_, Station stationB_){
        return graphe_.getTrajets().stream().anyMatch(
            (t) -> (t.getDepart().equals(stationA_) && t.getArrive().equals(stationB_)) 
            || (t.getDepart().equals(stationB_) && t.getArrive().equals(stationA_)) 
        );
    }

    public static Collection<Station> stationEstSurLaLigne(Graphe graphe_, int ligne_){
        HashSet<Station> stationSurLigne = new HashSet<Station>();
        graphe_.getTrajets()
            .stream()
            .forEach((t) -> {
                if(t.getLigne().getNumero()==ligne_){
                    stationSurLigne.add(t.getArrive());
                    stationSurLigne.add(t.getDepart());
                }
            }
        );
		return stationSurLigne;
    }

    public static Collection<Station> stationVoisines(Graphe graphe_, Station station_){
        Collection<Station> stations = new HashSet<Station>();
        graphe_.getTrajets()
            .stream()
            .forEach((t) -> {
                if(station_.equals(t.getDepart())){
                    stations.add(t.getArrive());
                }else if(station_.equals(t.getArrive())){
                    stations.add(t.getDepart());
                }
            }
        );
        return stations;
    }

    public static Collection<String> stationsVoisinesDe(Graphe graphe_, String nomDeStation_){
		Collection<String> nomStations = new HashSet<String>();
        graphe_.getTrajets()
            .stream()
            .forEach((t) -> {
                if(Reseau.modifierNom(nomDeStation_).equals(Reseau.modifierNom(t.getDepart().getNom()))){
                    nomStations.add(Reseau.modifierNom(t.getArrive().getNom()));
                }else if(Reseau.modifierNom(nomDeStation_).equals(Reseau.modifierNom(t.getArrive().getNom()))){
                    nomStations.add(Reseau.modifierNom(t.getDepart().getNom()));
                }
            }
        );
        return nomStations;
	}
 

    public static ArrayList<Trajet> cheminDeVers(Graphe graphe_ , Station station1_, Station station2_){
        ArrayList<Trajet> trajet = new ArrayList<Trajet>();
        ArrayList<Trajet> chemin = new ArrayList<Trajet>();
        return  cheminDeVersRec(graphe_, station1_, station2_, trajet, chemin);
    }

    public static ArrayList<Trajet> cheminDeVersRec(Graphe graphe_ , Station station1_, Station station2_, ArrayList<Trajet> trajet_, ArrayList<Trajet> chemin_){
        
        if(station1_.equals(station2_))
            return trajet_;

        ArrayList<Trajet> trajet;
        ArrayList<Trajet> rec;
        ArrayList<Trajet> chemin = new ArrayList<Trajet>();
        for(Trajet tr : graphe_.getTrajets()){
            if((chemin_.isEmpty() || chemin_.size() > trajet_.size()) && !trajet_.contains(tr) ){
                if(station1_.equals(tr.getDepart()) ){

                    trajet = new ArrayList<Trajet>(trajet_);
                    trajet.add(tr);

                    Station arrive = tr.getArrive();
                    rec = cheminDeVersRec(graphe_, arrive, station2_, trajet, chemin_);
                    if(!rec.isEmpty() && (rec.size() < chemin_.size() || chemin_.isEmpty())){
                        chemin = new ArrayList<Trajet>(rec);
                    } 
                } /*else if(station1_.equals(tr.getArrive())){
                    trajet = (HashSet<Trajet>)trajet_.clone();
                    trajet.add(tr);

                    Station depart = tr.getArrive();
                    rec = cheminDeVersRec(graphe_, depart, station2_, trajet, chemin_);
                    if(!rec.isEmpty() && (rec.size() < chemin_.size() || chemin_.isEmpty())){
                        chemin = (HashSet<Trajet>)rec.clone();
                    }
                }*/
            }
        }
        return chemin;
    }
}