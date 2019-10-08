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
        HashSet<Trajet> trouve =  cheminDeVersRec(graphe_, station1_, station2_, trajet, chemin);
        return triOrdreTrajet(trouve,station1_,station2_);
    }

    public static HashSet<Trajet> cheminDeVersRec(Graphe graphe_ , Station station1_, Station station2_, Collection<Trajet> trajet_, Collection<Trajet> chemin_){
        
        if(station1_.equals(station2_))
            return trajet_;

        HashSet<Trajet> trajet;
        HashSet<Trajet> rec;
        HashSet<Trajet> chemin = new HashSet<Trajet>();
        for(Trajet tr : graphe_.getTrajets()){
            if((chemin_.isEmpty() || chemin_.size() > trajet_.size()) && !trajet_.contains(tr) ){
                if(station1_.equals(tr.getDepart()) ){

                    trajet = (HashSet<Trajet>)trajet_.clone();
                    trajet.add(tr);

                    Station arrive = tr.getArrive();
                    rec = cheminDeVersRec(graphe_, arrive, station2_, trajet, chemin_);
                    if(!rec.isEmpty() && (rec.size() < chemin_.size() || chemin_.isEmpty())){
                        chemin = (HashSet<Trajet>)rec.clone();
                    } 
                } else if(station1_.equals(tr.getArrive())){
                    trajet = (HashSet<Trajet>)trajet_.clone();
                    trajet.add(tr);

                    Station depart = tr.getArrive();
                    rec = cheminDeVersRec(graphe_, depart, station2_, trajet, chemin_);
                    if(!rec.isEmpty() && (rec.size() < chemin_.size() || chemin_.isEmpty())){
                        chemin = (HashSet<Trajet>)rec.clone();
                    }
                }
            }
        }
        return chemin;
    }

    public static HashSet<Trajet> triOrdreTrajet(Collection<Trajet> trajet_, Station depart_, Station arrive_){
        HashSet<Trajet> trier = new HashSet<Trajet>();
        Station depart = depart_;
        while(!depart.equals(arrive_)){
            for(Trajet tr : trajet_){
                if(depart.equals(tr.getDepart())){
                    trier.add(tr);
                    depart = tr.getArrive();
                }
            }
        }
    }



}