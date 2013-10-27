package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import affichage.AfficheHeure;

import modele.Alarme;
import modele.Chrono;
import modele.Heure;




import observer.Observateur;
/**
 * 
 * @author Emmanuel
 *
 * Classe Vue qui implements l'interface observateur 
 */
public class Vue implements Observateur{
	private JFrame frame;

	private JTextField textfieldh;
	private JButton button_up; 
	private JButton button_set;
	private JButton button_echap;
	
	public int nbetatAffichage = 0;
	
	private Timer timertemps = new Timer();
	private Timer tempsalarm = new Timer();
	
	private int alarmOnOff = 0;
	private boolean alarmeOk = false;
	private int clignotementAlarm = 0;
	
	private int mode = 0;
	
	public int clignotement = 0;
	public int heure_min_sec = 0;
	public int clig = 0;
	
	private int start_stop = 0;
	
	/**
	 *  
	 */
	public void changeH_M(){
		if(this.heure_min_sec == 0){
			this.heure_min_sec = 1;
			}else if(this.heure_min_sec == 1){
				this.heure_min_sec = 2;
			}else{
				this.heure_min_sec = 0;
			}
	}
	
	/**
	 * 
	 * @param mode - int
	 * fonction qui permet de mettre à jour le mode
	 */
	public void setmode(int mode){
		this.mode = mode;
		Heure.getInstance().mode = mode;
		heure_min_sec = 0;
	}
	
	/**
	 * fonction qui increments les données
	 */
	public void increments_H_M_S(){
		Heure.getInstance().mode = 1;
		if(this.heure_min_sec == 0){
			Heure.getInstance().miseAJourH();
		}else if(this.heure_min_sec == 1){
			Heure.getInstance().miseAJourM();
		}else if(this.heure_min_sec == 2){
			Heure.getInstance().miseAJourS();
		}
	}
	
	/**
	 * fonction pour activer ou desactiver l'alarm
	 */
	public void alarmActiv(){
		switch(alarmOnOff){
			case 0: alarmOnOff = 1;
					confirmAlarm();
					break;
			case 1: alarmOnOff = 0;
					stopAlarm();
					break;
		}
	}
	
	/**
	 * fonction pour incrementer les données de l'alarme
	 */
	public void increments_Ha_Ma_Sa(){
		if(this.heure_min_sec == 0){
			Alarme.getInstance().miseAJourH();
		}else if(this.heure_min_sec == 1){
			Alarme.getInstance().miseAJourM();
		}else if(this.heure_min_sec == 2){
			Alarme.getInstance().miseAJourS();
		}
	}

	/**
	 * fonction pour incrementer les jours,mois,années
	 */
	public void increments_D_M_Y(){
		Heure.getInstance().mode = 1;
		if(this.heure_min_sec == 0){
			Heure.getInstance().miseAJourD();
		}else if(this.heure_min_sec == 1){
			Heure.getInstance().miseAJourMo();
		}else if(this.heure_min_sec == 2){
			Heure.getInstance().miseAJourY();
		}
	}
	
	/**
	 * fonction qui sauvegarde l'heure modifié
	 */
	public void saveH(){
		Heure.getInstance().reglage();
	}
	
	/**
	 * fonction qui vérifie si l'heure indiquer par le calendar date est la meme que celle modifié par le calendar alarme
	 */
	public void confirmAlarm(){
		TimerTask task = new TimerTask()
		{
			@Override
			public void run() 
			{
				if(Alarme.getInstance().mode == 0){
					if(Alarme.getInstance().getAlarm().get(Calendar.HOUR_OF_DAY) == Heure.getInstance().getHeure().get(Calendar.HOUR_OF_DAY)){
						if(Alarme.getInstance().getAlarm().get(Calendar.MINUTE) == Heure.getInstance().getHeure().get(Calendar.MINUTE)){
							alarmeOk = true;
						}
						else{
							alarmeOk = false;
						}
					}else{
						alarmeOk = false;
					}
					clignotementAlarm++;
				}
				
				
			}	
		};
		tempsalarm.scheduleAtFixedRate(task, 0, 100);
	}
	
	/**
	 * fonction qui desactive l'alarme
	 */
	public void stopAlarm(){
		tempsalarm.cancel();
		tempsalarm = new Timer();
	}
	
	/**
	 * fonction qui sauvegarde la modification apportée
	 */
	public void saveConfirm(){
		if(Heure.getInstance().affichage == 0){
			Heure.getInstance().saveH();
		}
		if(Heure.getInstance().affichage == 1){
			Heure.getInstance().saveD();
		}
		if(Heure.getInstance().affichage == 2){
			Alarme.getInstance().saveA();
		}
	}
	
	/**
	 * la fonction qui lance la représentaiton graphique
	 */
	public Vue (){
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 400, 150);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 200);
		panel.setLayout(null);
		this.frame.getContentPane().add(panel);
		
		textfieldh = new JTextField();
		textfieldh.setBounds(90, 40, 200, 30);
		textfieldh.setEditable(false);
		
		button_up = new JButton();
		button_up.setBounds(300, 20, 60, 30);
		button_up.setText("UP");
		button_up.setName("button_up");
		/**
		 * on rajoute un écouteur sur l'évènement de la souris
		 */
		button_up.addMouseListener((new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				timertemps.cancel();
				timertemps = new Timer();
				
				if(nbetatAffichage<2 && mode==0){
					if(Heure.getInstance().affichage == 2){
						alarmActiv();
					}else{
						mode = 4;
						Heure.getInstance().tempsattente = 0;
						Heure.getInstance().changeChrono();
					}
				}else if(mode == 4 && start_stop%2 == 0){
					Chrono.getInstance().lancerChrono();
					start_stop++;
				}else if(mode == 4 && start_stop%2 != 0){
					Chrono.getInstance().stopChrono();
					start_stop++;
				}else if(nbetatAffichage<2 && mode==1){
					if(Heure.getInstance().affichage == 0){
						increments_H_M_S();
					}
					if(Heure.getInstance().affichage == 1){
						increments_D_M_Y();
					}
					if(Heure.getInstance().affichage == 2){
						increments_Ha_Ma_Sa();
					}
				}else if(nbetatAffichage>=2 && mode==0){
					
				}else if(nbetatAffichage>=2 && mode==1){
					
				}
				nbetatAffichage = 0;
				Heure.getInstance().inactif = 0;
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Heure.getInstance().inactif = 0;
				TimerTask taskss = new TimerTask()
				{
					@Override
					public void run() 
					{
						nbetatAffichage = nbetatAffichage+1;
					}	
				};
				timertemps.scheduleAtFixedRate(taskss, 0, 1000);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}));
		
		button_set = new JButton();
		button_set.setBounds(300, 60, 60, 30);
		button_set.setText("SET");
		button_set.setName("button_set");
		button_set.addMouseListener((new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				timertemps.cancel();
				timertemps = new Timer();
				//instruction
				if(nbetatAffichage<2 && mode==0){
					Heure.getInstance().changeAffiche();
				}else if(nbetatAffichage<2 && mode==1){
					changeH_M();
				}else if(nbetatAffichage>=2 && mode==0){
					if(Heure.getInstance().affichage == 2){
						Alarme.getInstance().mode = 1;
						Alarme.getInstance().initTampon();
					}
					mode = 1;
					clig = 0;
					saveH();
				}else if(mode==3){
					mode = 0;
				}else if(mode==4){
					start_stop = 0;
					Chrono.getInstance().stopChrono();
					Chrono.getInstance().resetChrono();
				}
				
				nbetatAffichage = 0;
				Heure.getInstance().inactif = 0;
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Heure.getInstance().inactif = 0;
				TimerTask taskss = new TimerTask()
				{
					@Override
					public void run() 
					{
						nbetatAffichage = nbetatAffichage+1;
						if(nbetatAffichage>=2 && mode == 0){
							clig = 1;
						} 
						
						if(nbetatAffichage>=2 && mode==1){
							saveConfirm();
							mode = 3;
						}
					}	
				};
				timertemps.scheduleAtFixedRate(taskss, 0, 1000);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}));
		
		button_echap = new JButton();
		button_echap.setBounds(5, 40, 80, 30);
		button_echap.setText("ECHAP");
		button_echap.setName("button_echap");
		button_echap.addMouseListener((new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Heure.getInstance().inactif = 0;
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Heure.getInstance().inactif = 0;
				Heure.getInstance().mode = 0;
				Heure.getInstance().setAffiche(new AfficheHeure());
				mode = 0;
				heure_min_sec = 0;
				Heure.getInstance().tempsattente = 1;
				Alarme.getInstance().mode = 0;
				start_stop = 0;
				Chrono.getInstance().stopChrono();
				Chrono.getInstance().resetChrono();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}));
		
		panel.add(textfieldh);
		panel.add(button_up);
		panel.add(button_set);
		panel.add(button_echap);
	
		
		this.frame.setVisible(true);
	}
	

	@Override
	public void updateH(int h, int m, int s,String separateur) {
		// TODO Auto-generated method stub
		String heure_date,min_mois,sec_an;
		if((h)<10)
			heure_date = "0"+h;
		else 
			heure_date = Integer.toString(h);
		
		if(m<10)
			min_mois = "0"+m;
		else 
			min_mois = Integer.toString(m);
		
		if(s<10)
			sec_an = "0"+s;
		else 
			sec_an = Integer.toString(s);
		
		Font police = new Font("DS-digital",Font.TYPE1_FONT,30);
		textfieldh.setFont(police);
		if((alarmOnOff == 1 && Heure.getInstance().affichage == 2) || (alarmeOk && clignotementAlarm%2==0 && alarmOnOff == 1 && Heure.getInstance().affichage == 0 )){
			textfieldh.setForeground(Color.GREEN);	
		}else{
			textfieldh.setForeground(Color.BLACK);
		}
		
		
		if((mode==0 || mode==3 || mode==4) && clig==0){
			textfieldh.setText(heure_date+" "+separateur+" "+min_mois+" "+separateur+" "+sec_an);
		}else if((mode == 1 || clig == 1) && heure_min_sec == 0 && clignotement == 0){
			textfieldh.setText(heure_date+" "+separateur+" "+min_mois+" "+separateur+" "+sec_an);
		}else if((mode == 1 || clig == 1) && heure_min_sec == 0 && clignotement == 1){
			textfieldh.setText("     "+separateur+" "+min_mois+" "+separateur+" "+sec_an);
		}else if((mode == 1 || clig == 1) && heure_min_sec == 1 && clignotement == 0){
			textfieldh.setText(heure_date+" "+separateur+" "+min_mois+" "+separateur+" "+sec_an);
		}else if((mode == 1 || clig == 1) && heure_min_sec == 1 && clignotement == 1){
			textfieldh.setText(heure_date+" "+separateur+"      "+separateur+" "+sec_an);
		}else if((mode == 1 || clig == 1) && heure_min_sec == 2 && clignotement == 0){
			textfieldh.setText(heure_date+" "+separateur+" "+min_mois+" "+separateur+" "+sec_an);
		}else if((mode == 1 || clig == 1) && heure_min_sec == 2 && clignotement == 1){
			textfieldh.setText(heure_date+" "+separateur+" "+min_mois+" "+separateur+"    ");
		}
		
	}
	
}
