package controleur;

import java.util.Timer;
import java.util.TimerTask;
import affichage.AfficheHeure;

import modele.Alarme;
import modele.Heure;

import vue.Vue;

/**
 * @author Emmanuel
 * La classe Controleur, est la classe qui initialise la montre, elle lance aussi la tache d'incr�mentation, la tache d'incrementation de la dur�e d'inactivit�,etc.
 */
public class Controleur{
	
	private Heure heure;
	private Vue vue;
	
	/**
	 * 
	 * @param heure -a Heure
	 * @see modele.Heure
	 * Le constructeur prend en param�tre une instanciation de la classe Heure qui est le mod�le et donc sur lequel le controleur vas pouvoir modifier les donn�es.
	 */
	public Controleur(Heure heure){
		this.heure = heure;
	}
	
	/**
	 * fonction qui permet de rafraichir l'affichage de l'heure en lan�ant la fonction rafraichir de la classe Heure.
	 * @see modele.Heure
	 */
	public void rafraichir(){
		this.heure.rafraichir();
	}
	
	/**
	 * fontion de mise � jour de l'heure,c'est-�-dire qui va lancer la fonction mise � jour de la classe heure
	 * @see modele.Heure 
	 */
	public void miseAJour(){
		this.heure.miseAJour();
	}

	/**
	 * permet d'intervertir la valeur de l'attribut clignotement de la classe vue afin de cr�� un effet de clignotement sur la vue pour un ou plusieurs donn�es.
	 * @see vue.Vue
	 */
	public void clignotement(){
		if(vue.clignotement == 0){
			vue.clignotement = 1;
		}else{
			vue.clignotement = 0;
		}
	}
	
	/**
	 * fonction qui permet de lancer la montre
	 * @see vue.Vue
	 * @see modele.Heure
	 */
	public void run(){
		
		/*
		 * On instancie une vue, puis on la rajoute dans la liste des observateurs des donn�es de la classe Heure
		 */
		vue = new Vue();
		this.heure.addObservateur(vue);
		
		/*
		 * On cr�� la t�che qui toute les 10 millisecondes lance la fonction clignotement et rafraichir.
		 */
		TimerTask task = new TimerTask()
		{
			@Override
			public void run() 
			{
					clignotement();
					rafraichir();
			}	
		};
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 10);
		
		/*
		 * On cr�� la t�che qui toute les secondes une fois lancer toute les secondes, lancera la fonction miseAJour puis incr�mentes la variable de preuve d'inactivit� et qui v�rifie que l'on n'est pas rest� inactif pendant au moin 5 secondes.
		 * Si oui, alors on remet la vue sur l'affichage de l'heure et on remet l'attribut mode de vue  � 0 ainsi que celle de l'alarm.
		 *
		 */
		TimerTask tasktempo = new TimerTask()
		{
			@Override
			public void run() 
			{		
				miseAJour();
				
				Heure.getInstance().inactif = Heure.getInstance().inactif + Heure.getInstance().tempsattente;
			
				if(Heure.getInstance().inactif>5){
					Heure.getInstance().setAffiche(new AfficheHeure());
					vue.setmode(0);
					Alarme.getInstance().mode = 0;
				}
				
			}	
		};
		
		Timer timertempo = new Timer();
		timertempo.scheduleAtFixedRate(tasktempo, 0, 1000);
		
	}
	
}
