package affichage;

import java.util.Calendar;

import modele.Alarme;
import modele.Heure;
/**
 * @author Emmanuel
 * La classe AfficheAlarm qui impl�mente la classe Affiche sert � afficher les donn�es relatives � l'alarm
 */
public class AfficheAlarm implements Affiche{

	/**
	 * Dans le constructeur on d�finit que la vue de l'alarm est la vue num�ro 2
	 */
	public AfficheAlarm(){
		Heure.affichage = 2;
	}
	
	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int affichedateouheure(Calendar calendar) {
		// TODO Auto-generated method stub
		return Alarme.getInstance().getAlarm().get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int affichemoisoumin(Calendar calendar) {
		// TODO Auto-generated method stub
		return Alarme.getInstance().getAlarm().get(Calendar.MINUTE);
	}

	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int afficheanousec(Calendar calendar) {
		// TODO Auto-generated method stub
		return Alarme.getInstance().getAlarm().get(Calendar.SECOND);
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
