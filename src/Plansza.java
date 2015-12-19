import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Plansza {
	private Element[][] tab;
	private int szerokosc, wysokosc;
	private Bohater bohater;
	
	public Plansza(String sciezka) throws FileNotFoundException{
		File plik  = new File(sciezka);
		Scanner skaner = new Scanner(plik);
		this.szerokosc = skaner.nextInt();
		this.wysokosc = skaner.nextInt();
		skaner.nextLine();
		tab = new Element[szerokosc][wysokosc];
		bohater = null;
		
		for(int j = 0; j < wysokosc; j++) {
			String linia = skaner.nextLine();
			for(int i = 0; i < szerokosc; i++) {
				switch(linia.charAt(i)) {
				case '0': // Tlo
					tab[i][j] = new Tlo(i,j);
					break;
				case '*': // podloga
					tab[i][j] = null;
					break;
				case '#': // sciana
					tab[i][j] = new Sciana(i, j);
					break;
				case 'S': // skrzynia
					tab[i][j] = new Skrzynia(i, j);
					break;
				case 'P': // punkt
					tab[i][j] = new Punkt(i, j);
					break;
				case 'B': // bohater
					if(bohater == null) {
						tab[i][j] = new Bohater(i, j);
						bohater = (Bohater)tab[i][j];
					} else {
						System.out.println("Proba stworzenia wiecej niz 1 gracza");
						tab[i][j] = null;
					}
					break;
				case '$': // bohater i punkt
					if(bohater == null) {
						tab[i][j] = new Bohater(i, j);
						bohater = (Bohater)tab[i][j];
						((Bohater)tab[i][j]).setPunkt(new Punkt(i, j));
					} else {
						System.out.println("Nie moze byc wiecej niz jeden bohater");
						tab[i][j] = new Punkt(i, j);
					}
					break;
				case '@':// skzynia i punkt
					tab[i][j] = new Skrzynia(i, j);
					((Skrzynia)tab[i][j]).setPunkt(new Punkt(i, j));
					break;
					default:
						System.out.println("Nie udalo sie zczytac");
				}
			}
		}
		skaner.close();
	}
	
	public Element getPole(int x, int y){
		return tab[x][y];		
	}
	
	public void setPole(Element e, int x, int y) {
		tab[x][y] = e;
	}
	
	public int getSzerokosc() {
		return szerokosc;
	}
	
	public int getWysokosc() {
		return wysokosc;
	}
	
	public Bohater getBohater() {
		return bohater;
	}
	
	public boolean sprawdzSkrzynie() {
		for(int i = 0; i < szerokosc; i++) {
			for(int j = 0; j < wysokosc; j++) {
				if(getPole(i, j) instanceof Skrzynia) {
					if(((Skrzynia)getPole(i, j)).getPunkt() == null) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
