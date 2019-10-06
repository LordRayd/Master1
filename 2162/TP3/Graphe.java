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
 

    public static Collection<Trajet> cheminDeVers(Graphe graphe_ , Station station1_, Station station2_){
        Collection<Trajet> chemin = new HashSet<Trajet>();
        return cheminDeVersRec(graphe_, station1_, station2_, chemin);
    }

    public static Collection<Trajet> cheminDeVersRec(Graphe graphe_ , Station station1_, Station station2_, Collection<Trajet> trajet_){
        if(station1_.equals(station2_))
            return trajet_;

        Collection<Trajet> chemin = new HashSet<Trajet>(trajet_);
        Collection<Trajet> resultatChemin = new HashSet<Trajet>();
        for(Trajet tr : graphe_.getTrajets()){
            chemin = new HashSet<Trajet>(trajet_);
            if(station1_.equals(tr.getDepart()) && !trajet_.contains(tr)){
                chemin.add(tr);
                Station depart = tr.getArrive();
                chemin =cheminDeVersRec(graphe_, depart, station2_, trajet_);
                if(!chemin.isEmpty() && chemin.size() < resultatChemin.size()){
                    resultatChemin = new HashSet<Trajet>(chemin);
                }      
            }
        }
        return resultatChemin;
    }
}