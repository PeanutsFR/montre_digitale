package observer;
/**
 * 
 * @author Emmanuel
 * 
 */
public interface Observable {
	 /**
	  * 
	  * @param obs - Observateur
	  * fonction permet d'ajouter des observateurs dans sa file
	  * @see observer.Observateur
	  */
	  public void addObservateur(Observateur obs);
	  
	  /**
	   * 
	   * @param h - int
	   * @param m - int
	   * @param s - int
	   * @param separateur - String
	   * 
	   * fonction qui notifie les observateurs des données pour la representation graphique
	   */
	  public void updateObservateurH(int h, int m,int s,String separateur);
	  
	  /**
	   * fonction qui permet de supprimer des observateurs de la file
	   */
	  public void delObservateur();
	}
