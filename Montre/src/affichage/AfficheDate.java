package affichage;

import java.util.Calendar;

import modele.Heure;
/**
 * @author Emmanuel
 * La classe AfficheDate qui implémente la classe Affiche sert à afficher les données relatives à la date
 */
public class AfficheDate implements Affiche{

	/**
	 * Dans le constructeur on définit que la vue de la date est la vue numéro 1
	 */
	public AfficheDate(){
		Heure.affichage = 1;
	}
	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int affichedateouheure(Calendar calendar) {
		// TODO Auto-generated method stub
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int affichemoisoumin(Calendar calendar) {
		// TODO Auto-generated method stub
		return (calendar.get(Calendar.MONTH)+1);
	}

	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int afficheanousec(Calendar calendar) {
		// TODO Auto-generated method stub
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * @see affichage.Affiche
	 */
	@Override
	public String afficheseparateur() {
		// TODO Auto-generated method stub
		return "/";
	}
	
}
