package main;


import modele.Heure;
import controleur.Controleur;
/**
 * @author Emmanuel
 * La classe Main, est la classe qui va lancer le programme.
 */
public class Main {
	
	/**
	 * 
	 * @param args
	 * @see controleur.Controleur
	 */
	public static void main(String[] args){
		/*
		 * on créé une instance de Controleur, puis on lance la fonction run qui initialise la montre
		 */
		Controleur controller = new Controleur(Heure.getInstance());
		controller.run();
	}
}
