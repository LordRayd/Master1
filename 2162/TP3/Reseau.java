import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.StringTokenizer;

public class Reseau {

	public Collection<Station> station;
	public Graphe graphe;
	
	public Reseau() {
		this.station = new HashSet<Station>();
		this.graphe = new Graphe();
	}
	
	public static Reseau CreeReseauAPartirDuFichier(String nomDeFichier_) 
	throws IOException{
		Reseau reseau = new Reseau();
		String line;
		try(BufferedReader br = new BufferedReader(new FileReader(nomDeFichier_))){
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line,"\"");
				while(st.hasMoreTokens()) {
					
					Ligne ligne = new Ligne(new Integer(st.nextToken().trim()));
					Station depart = new Station(modifierNom(st.nextToken()));
					st.nextToken();
					Station arrive = new Station(modifierNom(st.nextToken()));
					
					depart.ajouteLigne(ligne);
					arrive.ajouteLigne(ligne);
					ajouteStation(reseau, depart);
					ajouteStation(reseau, arrive);
					ajouteNoeudGraphe(reseau, depart, arrive,ligne);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reseau;
	}

	public static boolean ajouteStation(Reseau reseau_,Station station_) { 
        return reseau_.getStation().add(station_); 
	}

	public static boolean ajouteNoeudGraphe(Reseau reseau_ , Station stationDepart_, Station stationArrive_, Ligne ligne_){
		return Graphe.ajouteNoeud(reseau_.getGraphe(),stationDepart_,stationArrive_, ligne_);
	}

	public static boolean stationDansGraphe(Reseau reseau_, Station station_){
		return Graphe.stationEstUnSommet(reseau_.getGraphe(), station_);
	}

	
	public static boolean stationEstUnVoisinDe(Reseau reseau_, Station stationSommet_, Station stationNoeud_){
		return Graphe.estUnVoisinDe(reseau_.getGraphe(), stationSommet_, stationNoeud_);
	}
	
	public Collection<Station> stationSurLigne(Reseau reseau_, int ligne_){
		return Graphe.stationEstSurLaLigne(reseau_.getGraphe(), ligne_);
	}

	public static String[] stationsVoisinesDe(Reseau reseau_, String nomDeStation_){
		Collection<String> stations = Graphe.stationsVoisinesDe(reseau_.getGraphe(), nomDeStation_);
		if(stations.isEmpty()){
			return null;
		}
		String[] voisins = new String[stations.size()];
		int x = 0;
		for(String str : stations){
			voisins[x] = str;
			x = x + 1;
		}
		return voisins;
	}

	public Collection<Station> getStation() {
		return this.station;
	}

	public void setStation(Collection<Station> station_) {
		this.station = station_;
	}

	public Graphe getGraphe(){
		return this.graphe;
	}
	
	public String toString() {
		String toString = new String("Reseau\n");
		for(Station station : this.station) {
			toString += station.toString();
		}
		return toString;
	}

	public static String modifierNom(String nom_){
		return Normalizer
			.normalize(nom_, Normalizer.Form.NFD)
			.replaceAll("[^\\p{ASCII}]", "")
			.replaceAll(" ","")
			.toLowerCase()
			.replaceAll("[^a-z0-9]", "")
			;
	}

	public static ArrayList<Trajet> cheminDeVers(Reseau reseau_, Station station1_, Station station2_){
		return Graphe.cheminDeVers(reseau_.getGraphe(), station1_, station2_);
	}
		
}
