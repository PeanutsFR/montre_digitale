package modele;

import java.util.ArrayList;
import java.util.Calendar;

import affichage.Affiche;
import affichage.AfficheAlarm;
import affichage.AfficheChrono;
import affichage.AfficheDate;
import affichage.AfficheHeure;



import observer.Observable;
import observer.Observateur;
/**
 * 
 * @author Emmanuel
 * La classe Heure contient les données relatives à l'heure dans l'application.
 * Cette classe est un singleton.
 */
public class Heure implements Observable{
	
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	private static Heure heure = null;
	private static Calendar date;
	private Calendar reglage;
	
	private Affiche affiche = new AfficheHeure();
	public static int affichage = 0;
	
	public static int inactif = 0;
	
	public static int tempsattente = 1;
	
	public int mode = 0;
	
	/**
	 * Constructeur de la classe Heure qui initialise le Calendar date
	 */
	private Heure(){
		this.date = Calendar.getInstance();
	}
	
	/**
	 * 
	 * @return reglage - Calendar	
	 */
	public Calendar getReglage(){
		return reglage;
	}
	
	/**
	 * 
	 * @return date Calendar
	 */
	public Calendar getHeure(){
		return date;
	}
	
	/**
	 * 
	 * @param affiche
	 * setter de l'attribut affiche de la classe Heure
	 */
	public void setAffiche(Affiche affiche){
		this.affiche = affiche;
	}
	
	/**
	 * Initialise le Calendar de reglage avec le Calendar courant
	 */
	public void reglage(){
		this.reglage = Calendar.getInstance();
		this.reglage.set(Calendar.HOUR_OF_DAY,this.date.get(Calendar.HOUR_OF_DAY));
		this.reglage.set(Calendar.MINUTE,this.date.get(Calendar.MINUTE));
		this.reglage.set(Calendar.SECOND,this.date.get(Calendar.SECOND));
		
		this.reglage.set(Calendar.DAY_OF_MONTH,this.date.get(Calendar.DAY_OF_MONTH));
		this.reglage.set(Calendar.MONTH,this.date.get(Calendar.MONTH));
		this.reglage.set(Calendar.YEAR,this.date.get(Calendar.YEAR));
	}
	
	/**
	 * 
	 * @return heure - Heure c'est la methode qui permet d'acquerir une instance de la classe Heure
	 */
	public static Heure getInstance(){
		if(heure == null){
			heure = new Heure();
		}
		return heure;
	}
	/**
	 * fonction qui permet de changer d'affichage
	 */
	public void changeChrono(){
		setAffiche(new AfficheChrono());
	}
	
	/**
	 * fonction qui permet de changer d'affichage de manière cyclique
	 */
	public void changeAffiche(){
		switch(this.affichage){
			case 0: setAffiche(new AfficheDate());
					break;
				
			case 1:setAffiche(new AfficheAlarm());
					break;
				
			case 2:setAffiche(new AfficheHeure());
					break;
		}
	}
	
	/**
	 * fonction qui met à jour le Calendar date en incrementant de un les secondes
	 */
	public void miseAJour(){
		this.date.add(Calendar.SECOND, 1);
	}
	
	/**
	 * fonction qui met à jour le Calendar reglage en incrementant de un les heures
	 */
	public void miseAJourH(){
		this.reglage.add(Calendar.HOUR_OF_DAY, 1);
	}
	
	/**
	 * fonction qui met à jour le Calendar reglage en incrementant de un les minutes
	 */
	public void miseAJourM(){
		this.reglage.add(Calendar.MINUTE, 1);
	}
	
	/**
	 * fonction qui met à jour le Calendar reglage en incrementant de un les secondes
	 */
	public void miseAJourS(){
		this.reglage.add(Calendar.SECOND, 1);
	}
	
	/**
	 * fonction qui met à jour le Calendar reglage en incrementant de un les jours
	 */
	public void miseAJourD(){
		this.reglage.add(Calendar.DAY_OF_MONTH, 1);
	}
	
	/**
	 * fonction qui met à jour le Calendar reglage en incrementant de un les mois
	 */
	public void miseAJourMo(){
		this.reglage.add(Calendar.MONTH, 1);
	}
	
	/**
	 * fonction qui met à jour le Calendar reglage en incrementant de un les années
	 */
	public void miseAJourY(){
		this.reglage.add(Calendar.YEAR, 1);
	}
	
	/**
	 * setter de l'attribut date de la classe Heure
	 */
	public void setDate(Calendar newDate){
		this.date = newDate;
	}
	
	/**
	 * fonction qui permet de sauvegarder les modifications effectué par l'intermediaire du Calendar reglage pour les heures,minutes,secondes
	 */
	public void saveH(){
		this.date.set(Calendar.HOUR_OF_DAY,this.reglage.get(Calendar.HOUR_OF_DAY));
		this.date.set(Calendar.MINUTE,this.reglage.get(Calendar.MINUTE));
		this.date.set(Calendar.SECOND,this.reglage.get(Calendar.SECOND));
		mode = 0;
	}
	
	/**
	 * fonction qui permet de sauvegarder les modifications effectué par l'intermediaire du Calendar reglage pour les jours,mois,année
	 */
	public void saveD(){
		this.date.set(Calendar.DAY_OF_MONTH,this.reglage.get(Calendar.DAY_OF_MONTH));
		this.date.set(Calendar.MONTH,this.reglage.get(Calendar.MONTH));
		this.date.set(Calendar.YEAR,this.reglage.get(Calendar.YEAR));
		mode = 0;
	}
	
	/**
	 * fonction qui avertie la vue que des données doivent être mise à jour et lui envoie ces données
	 */
	public void rafraichir(){
		String separateur = this.affiche.afficheseparateur();
		int hd = 0;
		int mm = 0;
		int sy = 0;
		if(mode==0){
			 hd = this.affiche.affichedateouheure(this.date);
			 mm = this.affiche.affichemoisoumin(this.date);
			 sy = this.affiche.afficheanousec(this.date);
		}
		else{
			 hd = this.affiche.affichedateouheure(this.reglage);
			 mm = this.affiche.affichemoisoumin(this.reglage);
			 sy = this.affiche.afficheanousec(this.reglage);
		}
		updateObservateurH(hd,mm,sy,separateur);
	}
	
	/**
	 * @see observer.Observable
	 */
	@Override
	public void addObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		this.listObservateur.add(obs);
	}

	/**
	 * @see observer.Observable
	 */
	@Override
	public void updateObservateurH(int h, int m, int s,String separateur) {
		// TODO Auto-generated method stub
		for(Observateur obs : this.listObservateur )
		      obs.updateH(h, m, s, separateur);
	}

	/**
	 * @see observer.Observable
	 */
	@Override
	public void delObservateur() {
		// TODO Auto-generated method stub
		 this.listObservateur = new ArrayList<Observateur>();
	}
}
