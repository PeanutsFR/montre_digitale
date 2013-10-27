package modele;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 
 * @author Emmanuel
 * La classe Chrono contient les données relatives au chronomètre dans l'application.
 * Cette classe est un singleton.
 */
public class Chrono {

	private static Chrono chrono = null;
	private Calendar chronometre;
	private Timer chronolancer = new Timer();
	
	/**
	 * Dans le constructeur on initialise les parmètres du chronomètre, dans notre cas on utilise le type Calendar
	 */
	private Chrono(){
		chronometre = Calendar.getInstance();
		chronometre.set(Calendar.MINUTE,0);
		chronometre.set(Calendar.SECOND,0);
		chronometre.set(Calendar.MILLISECOND,0);
	}
	/**
	 * 
	 * @return chrono - Chrono c'est la methode qui permet d'acquerir une instance de la classe Chrono
	 */
	public static Chrono getInstance(){
		if(chrono == null){
			chrono = new Chrono();
		}
		return chrono;
	}
	
	/**
	 * 
	 * @return chronometre - le Calendar associé au donnée chronometre
	 */
	public Calendar getChrono(){
			return chronometre;
	}
	
	/**
	 * fonction qui permet lance la fonction incrementsMilli toute les 1 milliseconde
	 */
	public void lancerChrono(){
		TimerTask task = new TimerTask()
		{
			@Override
			public void run() 
			{
				incrementsMilli();
			}	
		};
		chronolancer.scheduleAtFixedRate(task, 0, 1);
	}
	
	/**
	 * fonction qui incrmente le chronometre d'une milliseconde
	 */
	public void incrementsMilli(){
		chronometre.add(Calendar.MILLISECOND, 1);
	}
	
	/**
	 * fonction qui stop la tache lancer dans la fonction lancerChrono
	 */
	public void stopChrono(){
		chronolancer.cancel();
		chronolancer = new Timer();
	}
	
	/**
	 * fonction qui permet de réinitialiser le chronomètre
	 */
	public void resetChrono(){
		chronometre.set(Calendar.MINUTE,0);
		chronometre.set(Calendar.SECOND,0);
		chronometre.set(Calendar.MILLISECOND,0);
	}
}

