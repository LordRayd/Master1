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
        return graphe_.getTrajets().stream().anyMatch((t) -> t.getDepart().getNom().equals(station_.getNom()));
    }

    public static boolean estUnVoisinDe(Graphe graphe_, Station stationA_, Station stationB_){
        return graphe_.getTrajets().stream().anyMatch(
            (t) -> (t.getDepart().getNom().equals(stationA_.getNom()) && t.getArrive().getNom().equals(stationB_.getNom())) 
            || (t.getDepart().getNom().equals(stationB_.getNom()) && t.getArrive().getNom().equals(stationA_.getNom())) 
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
                if(nomDeStation_.equals(t.getDepart().getNom())){
                    nomStations.add(t.getArrive().getNom());
                }else if(nomDeStation_.equals(t.getArrive().getNom())){
                    nomStations.add(t.getDepart().getNom());
                }
            }
        );
        return nomStations;
	}
 

    public static HashSet<Trajet> cheminDeVers(Graphe graphe_ , Station station1_, Station station2_){
        HashSet<Trajet> trajet = new HashSet<Trajet>();
        HashSet<Trajet> chemin = new HashSet<Trajet>();
        return cheminDeVersRec(graphe_, station1_, station2_, trajet, chemin);
    }

    public static HashSet<Trajet> cheminDeVersRec(Graphe graphe_ , Station station1_, Station station2_, HashSet<Trajet> trajet_, HashSet<Trajet> chemin){
        
        if(station1_.equals(station2_))
            return trajet_;

            HashSet<Trajet> trajet;
            HashSet<Trajet> rec;
        for(Trajet tr : graphe_.getTrajets()){
            if(chemin.isEmpty() || chemin.size() > trajet_.size() ){
                if(station1_.equals(tr.getDepart()) && !trajet_.contains(tr)){

                    trajet = trajet_.clone();
                    trajet.add(tr);

                    Station depart = tr.getArrive();
                    rec = cheminDeVersRec(graphe_, depart, station2_, trajet, chemin);
                    if(!rec.isEmpty() && (rec.size() < chemin.size() || chemin.isEmpty())){
                        chemin = rec.clone();
                    }      
                }
            }
        }
        return chemin;
    }

}