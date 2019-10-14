import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Launcher {

	public static void lireMetro(String nomDeFichier_) throws IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(nomDeFichier_))){
			String line,depart, arrive;
			int ligne;
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line,"\"");
				while(st.hasMoreTokens()) {
					ligne = new Integer(st.nextToken().trim());
					depart = st.nextToken();
					st.nextToken(); // espace 
					arrive = st.nextToken();
					System.out.println("n°:"+ligne+" depart: "+depart+" arrive :"+arrive);
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public static void main(String[] args) {
		/*
		try {
			lireMetro("metro.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/*
		Ligne l1 = new Ligne(1);
		Ligne l2 = new Ligne(2);
		Ligne l3 = new Ligne(3);
		Ligne l4 = new Ligne(3);
		
		Station st = new Station("nomStation");
		st.ajouteLigne(l1);
		st.ajouteLigne(l2);
		st.ajouteLigne(l3);
		st.ajouteLigne(l4);
		
		for(Ligne l : st) {
			System.out.println("num : " + l.numero);
		}*/
		
		try {
			Reseau reseau = Reseau.CreeReseauAPartirDuFichier("metro.txt");
			/*for (Station st : reseau.getStation()){
				System.out.println(st.toString());
			}*/
			//System.out.println(reseau.toString());
			
			/*for(Trajet t : reseau.getGraphe().getTrajets()){
				System.out.println("depart : "+ t.getDepart() + " arrive : " + t.getArrive());
			}
			reseau.graphe.forEach((key, value) -> {
				System.out.println("Station : " + key);
				for(String str : value){
					System.out.println("Voisin : " + str);
				}
			});*/
			
			/*System.out.println("Voisin de : Ranelagh");
			String[] voisins = Reseau.stationsVoisinesDe(reseau,"Ranelagh");
			Arrays.stream(voisins).forEach(voisin -> System.out.println(Reseau.modifierNom(voisin)));*/
			System.out.println("Nombre de stations : "+reseau.getStation().size() + " trajets");
			//reseau.getStation().stream().sorted(Comparator.comparing(Station::getNom)).forEach((st) -> System.out.println(st.getNom()));
			
			System.out.println("Chemin de Reuilly — Diderot vers Châtelet");
			ArrayList<Trajet> chemin = Reseau.cheminDeVers(reseau,new Station("Reuilly — Diderot"), new Station("Châtelet"));
			System.out.println("Chemin le plus court en " + chemin.size());
			for(Trajet tr : chemin){
				System.out.println(tr.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
