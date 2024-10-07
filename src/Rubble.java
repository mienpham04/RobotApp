import javafx.scene.paint.Color;

public class Rubble extends GameObject {
	public static final int NUM_RUBBLES = 3;
    
	public Rubble(int row, int col) {
		super(row, col);
		setFill(Color.GREEN);
	}
}
