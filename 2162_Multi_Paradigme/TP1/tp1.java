
public class tp1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = {17,90,32,85,76,22,80,20,36,42,50,62,72,80,99,91,46};
		System.out.println("Somme : " + sommeRec(data)+"\n");

		System.out.println("Trouve 76 dans data : " + trouveRec(data,76));
		System.out.println("Trouve 2 dans data : " + trouveRec(data,2)+"\n");

		System.out.println("Nombre de valeur inferieure a 40 : " + combienInfARec(data,40));
		System.out.println("Nombre de valeur inferieure a 90 : " + combienInfARec(data,90)+"\n");
		
		int[][] tabPairImpair = separePairImpairRec(data);
		for(int i= 0 ; i<2; i++){
			if(i==0){
				System.out.println("Tableau Pair");
			}else{
				System.out.println("Tableau Impair");
			}
			for(int j= 0 ; j<tabPairImpair[i].length; j++){
				System.out.println("Indice " + j + " valeur : "+ tabPairImpair[i][j]);
			}
		}
		System.out.println();

		System.out.println("Inversion");
		int[]inverse = inverseRec(data);
		for(int i= 0 ; i<inverse.length; i++){
			System.out.println("Indice " + i + " valeur : "+ inverse[i]);
		}
	}

	static int sommeRecUtil(int[] tab_, int pos_) {
		if(pos_>= 0){
			if(pos_ < tab_.length-1){
				return tab_[pos_] + sommeRecUtil(tab_,pos_+1);
			}else{
				return tab_[pos_];
			}
		}
		return 0;
	}

	static int sommeRec(int[] tab_) {
		if(tab_ != null && tab_.length > 0){
			return sommeRecUtil(tab_,0);
		}else{
			return 0;
		}
	}

	static boolean trouveRecUtil(int[] tab_, int cherche_, int pos_){
		if(pos_>= 0){
			if(tab_[pos_] == cherche_){
				return true;
			}
			if(pos_ < tab_.length-1){
				return trouveRecUtil(tab_,cherche_,pos_+1);
			} else {
				return false;
			}
		}
		return false;
	}

	static boolean trouveRec(int[] tab_, int cherche_){
		if(tab_ != null && tab_.length > 0){
			return trouveRecUtil(tab_,cherche_,0);
		}else{
			return false;
		}
	}
	
	static int combienInfARecUtil(int[] tab_, int limiteSup_, int pos_){
		int nb = 0;
		if(pos_>= 0){
			if(tab_[pos_] < limiteSup_){
				nb = 1;
			}
			if(pos_ < tab_.length-1){
				nb += combienInfARecUtil(tab_,limiteSup_,pos_+1);
			}
		}
		return nb;
	}

	static int combienInfARec(int[] tab_, int limiteSup_){
		if(tab_ != null && tab_.length > 0){
			return combienInfARecUtil(tab_,limiteSup_,0);
		}else{
			return 0;
		}
	}

	static int[][] separePairImpairRecUtil(int[] tab_, int pos_){
		int[][] ret = new int[2][];
		boolean pair = false;
		int pos = 0;
		int nbPair = 0;
		int nbImpair = 0;
		int[][] tabRet = null;
		if(pos_ >= 0){
			if( pos_ < tab_.length-1){
				tabRet = separePairImpairRecUtil(tab_,pos_+1);
				nbPair = tabRet[0].length;
				nbImpair = tabRet[1].length;
			}
			if((tab_[pos_] % 2) == 0){
				ret[0] = new int[1+nbPair];
				ret[1] = new int[nbImpair];
				ret[0][0] = tab_[pos_];
				pair = true;
			} else {
				ret[0] = new int[nbPair];
				ret[1] = new int[1+nbImpair];
				ret[1][0] = tab_[pos_];
			}
			if(tabRet != null){
				for(int i = 0; i < 2; i++){
					pos = 0;
					if((i==0 && pair) || (i==1 && !pair)){
						pos = 1;
					}
					for(int j = 0; j < tabRet[i].length; j++){
						ret[i][pos] = tabRet[i][j];
						pos++;
					}
				}
			}
		}
		return ret;
	}

	static int[][] separePairImpairRec(int[] tab_){
		if(tab_ != null && tab_.length > 0){
			return separePairImpairRecUtil(tab_,0);
		}else{
			return null;
		}
	}

	static int[] inverseRecUtil(int[]tab_, int pos_){
		if(pos_ >= 0 && pos_ < tab_.length){
			int nbValeur = tab_.length - pos_;
			int[] ret = new int[nbValeur];
			if(pos_ < tab_.length - 1){
				int[] tabRec = inverseRecUtil(tab_, pos_+1);
				for(int i=0; i<nbValeur-1; i++){
					ret[i] = tabRec[i];
				}
			}
			ret[nbValeur-1] = tab_[pos_];
			return ret;
		}
		return null;
	}

	static int[] inverseRec(int[] tab_){
		if(tab_ != null && tab_.length > 0){
			return inverseRecUtil(tab_,0);
		}else{
			return null;
		}
	}
 
}
