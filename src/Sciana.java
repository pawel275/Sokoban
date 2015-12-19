import javax.swing.ImageIcon;

public class Sciana extends Element{
	private static ImageIcon obraz = new ImageIcon("obrazki/sciana.png");
	
	@Override
	public ImageIcon getObraz() {
		return obraz;
	}
	
	public Sciana(int x, int y) {
		super(x, y);
	}
	@Override
	public boolean przesun(Main.kierunek kierunek) {
		return false;
	}
	
}
