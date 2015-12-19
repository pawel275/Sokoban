import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Klawisze implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		if(Main.getKlawiszeAktywne()) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				Main.getPlansza().getBohater().przesun(Main.kierunek.Gora);
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				Main.getPlansza().getBohater().przesun(Main.kierunek.Dol);
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				Main.getPlansza().getBohater().przesun(Main.kierunek.Lewa);
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Main.getPlansza().getBohater().przesun(Main.kierunek.Prawa);
			}	
			Main.dodajRuch();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
