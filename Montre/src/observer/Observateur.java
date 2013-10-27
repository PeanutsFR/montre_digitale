package observer;
/**
 * 
 * @author Emmanuel
 *
 */
public interface Observateur {
	/**
	 * 
	 * @param h - int
	 * @param m - int
	 * @param s - int
	 * @param separateur - String
	 * 
	 * Fonction qui mettra a jour les nouvelles donn�es re�us par le mod�le.
	 */
	  public void updateH(int h,int m,int s,String separateur);
	}
