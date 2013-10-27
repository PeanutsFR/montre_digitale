package affichage;

import java.util.Calendar;

import modele.Heure;
/**
 * @author Emmanuel
 * La classe AfficheHeure qui impl�mente la classe Affiche sert � afficher les donn�es relatives � l'heure
 */
public class AfficheHeure implements Affiche{
	
	/**
	 * Dans le constructeur on d�finit que la vue de l'heure est la vue num�ro 0
	 */
	public AfficheHeure(){
		Heure.affichage = 0;
	}
	
	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int affichedateouheure(Calendar calendar) {
		// TODO Auto-generated method stub
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int affichemoisoumin(Calendar calendar) {
		// TODO Auto-generated method stub
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int afficheanousec(Calendar calendar) {
		// TODO Auto-generated method stub
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * @see affichage.Affiche
	 */
	@Override
	public String afficheseparateur() {
		// TODO Auto-generated method stub
		return ":";
	}

}
