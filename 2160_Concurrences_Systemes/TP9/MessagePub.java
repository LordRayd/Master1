import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Application d'affichage de  messages publicitaires
 */
 
/**
 * Afficheur
 */
public  class MessagePub extends Thread{

  
	
	static SynchroLecteurRedacteur Synchro =new SynchroLecteurRedacteurWaitNotify();
	//= new SynchroLecteurRedacteurSemaphore();
	// 
	
	static String Messages[];
	static final int NBAFF=3;


	private  int id;

	public MessagePub(int id){
		this.id=id;
	}


	public void run() {

		while(true) {


			try {
				// synchro de debut
				Synchro.debutLire();

				if (Messages[id]!= null)
					System.out.println( Messages[id]);
				//  synchro de fin
				Synchro.finLire();

				Thread.sleep(1000);
			} catch ( Exception  e) {

			} 
		}
	}






	public static void main (String[] args)  {

		Messages = new String[NBAFF];

		for (int i=0; i < NBAFF;i++){
			//			MessagePub mp= new MessagePub(i);
			//			mp.start();
			(new MessagePub(i)).start();
		}

		BufferedReader Clavier = new BufferedReader(new InputStreamReader(System.in));
		int num;

		System.out.println("Saisir un numero d'afficheur < "+NBAFF);

		try {
			while(true) {

				do {
					num=  Integer.parseInt(Clavier.readLine());
				} while (num < 0 || num >= NBAFF);

				// synchro 
				Synchro.debutEcrire();

				System.out.println("Saisir le message");

				Messages[num]=Clavier.readLine();	

				// liberer la ressource
				Synchro.finEcrire();
			}

		} catch ( Exception e) {
			e.printStackTrace();
		}
	} // end of try-catch




} // end of main ()