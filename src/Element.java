import javax.swing.ImageIcon;

public abstract class Element {
	protected int x, y;

	public Element(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public final int getX() {
		return x;
	}
	
	public final int getY() {
		return y;
	}
	
	public abstract ImageIcon getObraz();
	
	public abstract boolean przesun(Main.kierunek kierunek);
}
