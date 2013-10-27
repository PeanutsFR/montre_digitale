package affichage;

import java.util.Calendar;
/**
 * L'interface Affiche permet une fois implement�, d'acc�der au m�thode disponible afin de pouvoir afficher sur l'ecran
 */
public interface Affiche {
	/**
	 * 
	 * @param calendar -a Calendar
	 * @return int - retourne l'entier qui correspond � la valeur qui sera affich� tout � gauche de l'�cran dans notre cas soit l'heure pour l'affichage del'heure, soit le jour du mois pour la date ou bien encore les minutes pour le chronom�tre 
	 */
	public int affichedateouheure(Calendar calendar);
	/**
	 * 
	 * @param calendar -a Calendar
	 * @return int - retourne l'entier qui correspond � la valeur qui sera affich� au milieu de l'�cran dans notre cas soit les minutes pour l'affichage del'heure, soit le mois pour la date ou bien encore les secondes pour le chronom�tre 
	 */
	public int affichemoisoumin(Calendar calendar);
	/**
	 * 
	 * @param calendar -a Calendar
	 * @return int - retourne l'entier qui correspond � la valeur qui sera affich� tout � droite de l'�cran dans notre cas soit les secondes pour l'affichage del'heure, soit l'ann�e pour la date ou bien encore les millisecondes pour le chronom�tre 
	 */
	public int afficheanousec(Calendar calendar);
	/**
	 * 
	 * @return 	String - retourne la chaine ou le caract�re qui s�pare les diff�rents entiers
	 */
	public String afficheseparateur();
}
