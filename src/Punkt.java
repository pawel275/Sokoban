import javax.swing.ImageIcon;

public class Punkt extends Element{
	private static ImageIcon obraz = new ImageIcon("obrazki/punkt.png");
	
	@Override
	public ImageIcon getObraz() {
		return obraz;
	}
		
	public Punkt(int x, int y) {
		super(x, y);
	}
	@Override
	public boolean przesun(Main.kierunek kierunek) {
		return true;
	}
	
}