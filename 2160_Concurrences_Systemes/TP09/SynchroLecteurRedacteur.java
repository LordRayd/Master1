import java.util.concurrent.*;

/**
 * Classe Abstraite SynchroLecteurRedacteur
 *     Definit la synchornisation d'un algo de type lecteurs/redacteur
 **/
 
public abstract class SynchroLecteurRedacteur{

    /**
     * Lecteurs
     */
	public abstract void debutLire( );
	public abstract void finLire( );
	/**
	 * Redacteur
	 */
	public abstract void debutEcrire( );
	public abstract void finEcrire( );
}