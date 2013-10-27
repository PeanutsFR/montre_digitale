package affichage;

import java.util.Calendar;
import modele.Chrono;
import modele.Heure;
/**
 * @author Emmanuel
 * La classe AfficheChrono qui impl�mente la classe Affiche sert � afficher les donn�es relatives au chronom�tre
 */
public class AfficheChrono implements Affiche{

	/**
	 * Dans le constructeur on d�finit que la vue du chrono est la vue num�ro 3
	 */
	public AfficheChrono(){
		Heure.affichage = 3;
	}
	
	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int affichedateouheure(Calendar calendar) {
		// TODO Auto-generated method stub
		return Chrono.getInstance().getChrono().get(Calendar.MINUTE);
	}

	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int affichemoisoumin(Calendar calendar) {
		// TODO Auto-generated method stub
		return Chrono.getInstance().getChrono().get(Calendar.SECOND);
	}

	/**
	 * @see affichage.Affiche
	 */
	@Override
	public int afficheanousec(Calendar calendar) {
		// TODO Auto-generated method stub
		return Chrono.getInstance().getChrono().get(Calendar.MILLISECOND);
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
