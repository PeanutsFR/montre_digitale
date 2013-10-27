package affichage;

import java.util.Calendar;
/**
 * L'interface Affiche permet une fois implementé, d'accéder au méthode disponible afin de pouvoir afficher sur l'ecran
 */
public interface Affiche {
	/**
	 * 
	 * @param calendar -a Calendar
	 * @return int - retourne l'entier qui correspond à la valeur qui sera affiché tout à gauche de l'écran dans notre cas soit l'heure pour l'affichage del'heure, soit le jour du mois pour la date ou bien encore les minutes pour le chronomètre 
	 */
	public int affichedateouheure(Calendar calendar);
	/**
	 * 
	 * @param calendar -a Calendar
	 * @return int - retourne l'entier qui correspond à la valeur qui sera affiché au milieu de l'écran dans notre cas soit les minutes pour l'affichage del'heure, soit le mois pour la date ou bien encore les secondes pour le chronomètre 
	 */
	public int affichemoisoumin(Calendar calendar);
	/**
	 * 
	 * @param calendar -a Calendar
	 * @return int - retourne l'entier qui correspond à la valeur qui sera affiché tout à droite de l'écran dans notre cas soit les secondes pour l'affichage del'heure, soit l'année pour la date ou bien encore les millisecondes pour le chronomètre 
	 */
	public int afficheanousec(Calendar calendar);
	/**
	 * 
	 * @return 	String - retourne la chaine ou le caractère qui sépare les différents entiers
	 */
	public String afficheseparateur();
}
