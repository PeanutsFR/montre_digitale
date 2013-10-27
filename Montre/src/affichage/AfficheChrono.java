package affichage;

import java.util.Calendar;
import modele.Chrono;
import modele.Heure;
/**
 * @author Emmanuel
 * La classe AfficheChrono qui implémente la classe Affiche sert à afficher les données relatives au chronomètre
 */
public class AfficheChrono implements Affiche{

	/**
	 * Dans le constructeur on définit que la vue du chrono est la vue numéro 3
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
