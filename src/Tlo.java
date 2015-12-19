import javax.swing.ImageIcon;

public class Tlo extends Element{
	private static ImageIcon obraz = new ImageIcon("obrazki/tlo.png");
	
	@Override
	public ImageIcon getObraz() {
		return obraz;
	}
	
	public Tlo(int x, int y) {
		super(x, y);
	}
	@Override
	public boolean przesun(Main.kierunek kierunek) {
		return false;
	}
}