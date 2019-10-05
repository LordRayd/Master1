import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
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
			/*
			//System.out.println(reseau.toString());
			for(Trajet t : reseau.getGraphe().getTrajets()){
				System.out.println("depart : "+ t.getDepart() + " arrive : " + t.getArrive());
			}
			reseau.graphe.forEach((key, value) -> {
				System.out.println("Station : " + key);
				for(String str : value){
					System.out.println("Voisin : " + str);
				}
			});
			*/
			/*System.out.println("Voisin de : Ranelagh");
			String[] voisins = Reseau.stationsVoisinesDe(reseau,"Ranelagh");
			Arrays.stream(voisins).forEach(voisin -> System.out.println(Reseau.modifierNom(voisin)));*/
			/*System.out.println("Nombre de stations : "+reseau.getStation().size());
			reseau.getStation().stream().sorted(Comparator.comparing(Station::getNom)).forEach((st) -> System.out.println(st.getNom()));*/
			//Valeur de 20 pour la pile de recherche
			//pile plus grand que chemin arret
			String accents 	= "È,É,Ê,Ë,Û,Ù,Ï,Î,À,Â,Ô,è,é,ê,ë,û,ù,ï,î,à,â,ô,Ç,ç,Ã,ã,Õ,õ";
			String expected	= "E,E,E,E,U,U,I,I,A,A,O,e,e,e,e,u,u,i,i,a,a,o,C,c,A,a,O,o";
			
			String accents2	= "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
			String expected2	= "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";
			
			String accents3	= "Gisele Bündchen da Conceição e Silva foi batizada assim em homenagem à sua conterrânea de Horizontina, RS.";
			String expected3	= "Gisele Bundchen da Conceicao e Silva foi batizada assim em homenagem a sua conterranea de Horizontina, RS.";
			String accents4	= "/Users/rponte/arquivos-portalfcm/Eletron/Atualização_Diária-1.23.40.exe";
			String expected4	= "/Users/rponte/arquivos-portalfcm/Eletron/Atualizacao_Diaria-1.23.40.exe";
			
			System.out.println(Reseau.unaccent(accents));
			System.out.println(Reseau.unaccent(accents2));
			System.out.println(Reseau.unaccent(accents3));
			System.out.println(Reseau.unaccent(accents4));
				/*assertEquals(expected2, StringUtils.unaccent(accents2));
				assertEquals(expected3, StringUtils.unaccent(accents3));
				assertEquals(expected4, StringUtils.unaccent(accents4));*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
