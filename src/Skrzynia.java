import javax.swing.ImageIcon;

public class Skrzynia extends Element{
	private static ImageIcon obraz1 = new ImageIcon("obrazki/skrzynia.png");
	private static ImageIcon obraz2 = new ImageIcon("obrazki/skrzynia2.png");
	private Punkt punkt;
	
	@Override
	public ImageIcon getObraz() {
		if(punkt == null) {
			return obraz1;
		} else {
			return obraz2;
		}
	}
	
	public Skrzynia(int x, int y) {
		super(x, y);
		punkt = null;
	}
	
	public Punkt getPunkt() {
		return punkt;
	}

	public void setPunkt(Punkt punkt) {
		this.punkt = punkt;
	}

	@Override
	public boolean przesun(Main.kierunek kierunek) {
		int dx = 0, dy = 0;
		switch(kierunek) {
		case Gora:
			dx = 0;
			dy = -1;
			break;
		case Dol:
			dx = 0;
			dy = 1;
			break;
		case Lewa:
			dx = -1;
			dy = 0;
			break;
		case Prawa:
			dx = 1;
			dy = 0;
			break;
		}	
		if(Main.getPlansza().getPole(x+dx, y+dy) == null) {
			Main.getPlansza().setPole(this, x+dx, y+dy);
			Main.getPlansza().setPole(punkt, x, y);	
			x += dx;
			y += dy;
			punkt = null;
			return true;
		} else if(Main.getPlansza().getPole(x+dx, y+dy) instanceof Skrzynia) {
			return false;
		} else if(Main.getPlansza().getPole(x+dx, y+dy).przesun(kierunek)) {
			Punkt tmp = punkt;
			punkt = (Punkt)Main.getPlansza().getPole(x+dx, y+dy);
			Main.getPlansza().setPole(this, x+dx, y+dy);
			Main.getPlansza().setPole(tmp, x, y);
			x += dx;
			y += dy;
			return true;
		} else {
			return false;
		}
	}
}
