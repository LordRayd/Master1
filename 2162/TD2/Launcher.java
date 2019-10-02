import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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
		
		/*Ligne l1 = new Ligne(1);
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
			//System.out.println(reseau.toString());
			/*reseau.graphe.forEach((key, value) -> {
				System.out.println("Station : " + key);
				for(String str : value){
					System.out.println("Voisin : " + str);
				}
			});*/
			System.out.println("Voisin de : Ranelagh");
			String[] voisins = reseau.stationsVoisinesDe("Ranelagh");
			Arrays.stream(voisins).forEach(voisin -> System.out.println(voisin));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
