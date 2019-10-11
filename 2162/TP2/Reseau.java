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
	public Map<String,Collection<String>> graphe;
	
	public Reseau() {
		this.station = new HashSet<Station>();
		this.graphe = new HashMap<String,Collection<String>>();
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
					
					for(Station station : reseau.getStation()) {
						if(station.getNom().equals(depart.getNom()) || station.getNom().equals(arrive.getNom())) {
							station.ajouteLigne(ligne);
						}
					}
					depart.ajouteLigne(ligne);
					arrive.ajouteLigne(ligne);
					reseau.ajouteStation(depart);
					reseau.ajouteStation(arrive);

					//Ajoute le depart au graphe avec pour voisin arriver
					if(!reseau.graphe.containsKey(depart.getNom())){
						HashSet<String> list = new HashSet<String>();
						list.add(arrive.getNom());
						reseau.graphe.put(depart.getNom(),list);
					}else{
						if( reseau.graphe.isEmpty() || !reseau.graphe.get(depart.getNom()).contains(arrive.getNom()))
							reseau.graphe.get(depart.getNom()).add(arrive.getNom());
					}

					//Ajoute arriver au graphe avec départ pour voisin
					if(!reseau.graphe.containsKey(arrive.getNom())){
						HashSet<String> list = new HashSet<String>();
						list.add(depart.getNom());
						reseau.graphe.put(arrive.getNom(),list);
					}else{
						if(reseau.graphe.isEmpty() || !reseau.graphe.get(arrive.getNom()).contains(depart.getNom()))
							reseau.graphe.get(arrive.getNom()).add(depart.getNom());
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reseau;
	}

	public void ajouteStation(Station station_) { 
        this.station.add(station_); 
	}

	public Collection<Station> stationSurLigne(int ligne_){
		HashSet<Station> stationSurLigne = new HashSet<Station>();
		for(Station st : this.station){
			if(st.getLignes().contains(ligne_))
				stationSurLigne.add(st);
		}
		return stationSurLigne;
	}

	public String[] stationsVoisinesDe(String nomDeStation_){
		if(this.graphe.containsKey(nomDeStation_)){
			String[] voisins = new String[this.graphe.get(nomDeStation_).size()];
			int x = 0;
			for(String str : this.graphe.get(nomDeStation_)){
				voisins[x] = str;
				x = x + 1;
			}
			return voisins;
		}
		return null;
	}

	public Collection<Station> getStation() {
		return this.station;
	}

	public void setStation(Collection<Station> station_) {
		this.station = station_;
	}
	
	public String toString() {
		String toString = new String("Reseau\n");
		for(Station station : this.station) {
			toString += station.toString();
		}
		return toString;
	}

	public static String modifierNom(String nom_){
		return Normalizer.normalize(nom_.replaceAll(" ","")
			.toLowerCase(), Normalizer.Form.NFD)
			.replaceAll("[^\\p{ASCII}]", "");
	}
		
}
