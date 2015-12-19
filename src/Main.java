import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class Main extends JFrame{ 
	private static final long serialVersionUID = 1L;
	private static Plansza plansza;
	private static JLabel[][] etykiety;
	private static ImageIcon podloga = new ImageIcon("obrazki/podloga.png");
	
	private static JLabel labelRuchy;
	private static JLabel labelCzas;
	private static JLabel labelKomunikat;
	private static int iloscRuchow;
	private static long poczatekCzasu;
	private static final long limitCzasu = 2*60*1000;
	private static boolean klawiszeAktywne;
	
	public String sciezka;
	private static Main gra;
	
	public static enum kierunek {
		Lewa, Prawa, Gora, Dol
	}
	
	public static void main(String[] args) {
		gra = new Main();
		gra.setVisible(true);
		gra.setFocusable(true);
		while(true) {
			try {
				odswiez();
			} catch (Exception e) {}
		}
	}

	public static void odswiez() {
		for(int i = 0; i < plansza.getSzerokosc(); i++) {
			for(int j = 0; j < plansza.getWysokosc(); j++) {
				if(plansza.getPole(i, j) == null) {
					etykiety[i][j].setIcon(podloga);
				} else {
					etykiety[i][j].setIcon(plansza.getPole(i, j).getObraz());
				}
			}
		}
		if(plansza.sprawdzSkrzynie()) {
			setKlawiszeAktywne(false);
			labelKomunikat.setText("Wygrales!");
		} else {
			if(poczatekCzasu+limitCzasu-System.currentTimeMillis()<=0) {
				setKlawiszeAktywne(false);
				labelKomunikat.setText("Koniec czasu. Przegrales.");
			} else {
				setKlawiszeAktywne(true);
				labelCzas.setText(new SimpleDateFormat("mm:ss").format(poczatekCzasu+limitCzasu-System.currentTimeMillis()));
				labelRuchy.setText("Ruchy: " + iloscRuchow);	
			}
		}
	}
	
	public static void dodajRuch() {
		iloscRuchow++;
	}
	
	public static void setKlawiszeAktywne(boolean k) {
		klawiszeAktywne = k;
	}
	
	public static boolean getKlawiszeAktywne() {
		return klawiszeAktywne;
	}
	
	public static Plansza getPlansza() {
		return plansza;
	}
	
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		addKeyListener(new Klawisze());
		
		JLabel instrukcja = new JLabel("Podaj nazwê pliku z plansz¹", SwingConstants.CENTER);
		final JTextField poletekstowe = new JTextField();
		JButton wczytaj = new JButton("Wczytaj");
		poletekstowe.setPreferredSize(new Dimension(300, 30));
		instrukcja.setPreferredSize(new Dimension(300, 50));
		final JPanel start = new JPanel();
		start.setLayout(new BorderLayout(0,30));
		start.add(instrukcja,BorderLayout.NORTH);
		start.add(poletekstowe);
		start.add(wczytaj,BorderLayout.SOUTH);
		add(start);
		wczytaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sciezka = "mapy/"+poletekstowe.getText();
				try {
					plansza = new Plansza(sciezka);
					final JPanel panelGry = new JPanel();
					panelGry.setLayout(new GridLayout(plansza.getWysokosc(), plansza.getSzerokosc()));
					etykiety = new JLabel[plansza.getSzerokosc()][plansza.getWysokosc()];
					for(int j = 0; j < plansza.getWysokosc(); j++){
						for(int i = 0; i < plansza.getSzerokosc(); i++) {
							panelGry.add(etykiety[i][j] = new JLabel());
						}
					}
					poczatekCzasu = System.currentTimeMillis();
					iloscRuchow = 0;
					final JPanel panelDolny = new JPanel();
					final JButton restart = new JButton("Restart");
					final JButton nowaPlansza = new JButton("Nowa Plansza");
					restart.setFocusable(false);
					nowaPlansza.setFocusable(false);
					labelRuchy = new JLabel();
					labelCzas = new JLabel();
					labelKomunikat = new JLabel();
					panelDolny.add(restart);
					panelDolny.add(nowaPlansza);
					panelDolny.add(labelRuchy);
					panelDolny.add(labelCzas);
					panelDolny.add(labelKomunikat);
					remove(start);
					panelDolny.setLayout(new GridLayout(5, 1));
					add(panelDolny,BorderLayout.SOUTH);
					add(panelGry);
					
					restart.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								plansza = new Plansza(sciezka);
							} catch (FileNotFoundException e1) {}
							poczatekCzasu = System.currentTimeMillis();
							iloscRuchow = 0;
							labelKomunikat.setText("");
						}
					});
					nowaPlansza.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panelGry.setVisible(false);
							panelDolny.setVisible(false);
							add(start);
							pack();
							gra = new Main();
						}
					});
					pack();
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Podano b³êdn¹ nazwe pliku");
					poletekstowe.setText("");
				}
			}	
		});
		pack();
	}
}