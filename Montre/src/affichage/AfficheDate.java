package affichage;

import java.util.Calendar;

import modele.Heure;
/**
 * @author Emmanuel
 * La classe AfficheDate qui impl�mente la classe Affiche sert � afficher les donn�es relatives � la date
 */
public class AfficheDate implements Affiche{

	/**
	 * Dans le constructeur on d�finit que la vue de la date est la vue num�ro 1
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
