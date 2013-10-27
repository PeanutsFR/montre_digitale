package modele;

import java.util.Calendar;

/**
 * 
 * @author Emmanuel
 * La classe Alarme contient les données relatives à l'arme dans l'application.
 * Cette classe est un singleton.
 */
public class Alarme {

	private static Alarme alarme = null;
	private Calendar alarm;
	private Calendar alarm_tampon;
	public int mode = 0;
	
	/**
	 * Dans le constructeur on initialise les parmètres de l'alarme, dans notre cas on utilise le type Calendar
	 */ 
	private Alarme(){
		alarm = Calendar.getInstance();
		alarm.set(Calendar.HOUR_OF_DAY,0);
		alarm.set(Calendar.MINUTE,0);
		alarm.set(Calendar.SECOND,0);
	}
	
	/**
	 * 
	 * @return alarme - Alarme c'est la methode qui permet d'acquerir une instance de la classe Alarme
	 */
	public static Alarme getInstance(){
		if(alarme == null){
			alarme = new Alarme();
		}
		return alarme;
	}
	
	/**
	 * 
	 * @return alarm - le Calendar associé au donnée Alarme ou le Calendar associé à l'alarm pour sa modification
	 */
	public Calendar getAlarm(){
		if(mode == 0){
			return alarm;
		}else{
			return alarm_tampon;
		}
	}
	/**
	 * fonction qui permet de réinitialiser l'alarme
	 */
	public void resetAlarm(){
		alarm.set(Calendar.HOUR_OF_DAY,0);
		alarm.set(Calendar.MINUTE,0);
		alarm.set(Calendar.SECOND,0);
	}
	
	/**
	 * fonction qui permet d'enregistrer une modification effectué par l'utilisateur
	 */
	public void saveA(){
		this.alarm.set(Calendar.HOUR_OF_DAY,this.alarm_tampon.get(Calendar.HOUR_OF_DAY));
		this.alarm.set(Calendar.MINUTE,this.alarm_tampon.get(Calendar.MINUTE));
		this.alarm.set(Calendar.SECOND,this.alarm_tampon.get(Calendar.SECOND));
		mode = 0;
	}
	
	/**
	 * fonction qui initialise le Calendar utilisé pour effectué des modifications
	 */
	public void initTampon(){
		alarm_tampon = Calendar.getInstance();
		alarm_tampon.set(Calendar.HOUR_OF_DAY,alarm.get(Calendar.HOUR_OF_DAY));
		alarm_tampon.set(Calendar.MINUTE,alarm.get(Calendar.MINUTE));
		alarm_tampon.set(Calendar.SECOND,alarm.get(Calendar.SECOND));
	}
	
	/**
	 * permet de mettre à jour l'heure sur le calendar modification
	 */
	public void miseAJourH(){
		alarm_tampon.add(Calendar.HOUR_OF_DAY, 1);
	}
	
	/**
	 * permet de mettre à jour les minutes sur le calendar modification
	 */
	public void miseAJourM(){
		alarm_tampon.add(Calendar.MINUTE, 1);
	}
	
	/**
	 * permet de mettre à jour les secondes sur le calendar modification
	 */
	public void miseAJourS(){
		alarm_tampon.add(Calendar.SECOND, 1);
	}
}
