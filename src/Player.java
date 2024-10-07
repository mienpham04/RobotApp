import java.util.Random;
import javafx.scene.paint.Color;

public class Player extends GameObject
{
	private Random random = new Random();
	
	public Player()
	{
		super(GameBoard.NUM_ROWS / 2, GameBoard.NUM_COLS / 2);
		setFill(Color.RED);
	}

	public void move(int dRow, int dCol)
	{	
		if (GameBoard.isValid(this.row + dRow, this.col + dCol)) {
			this.row += dRow;
			this.col += dCol;
		}
		
		display();
	}
	
	public void randomMove() {
		int randX = random.nextInt(GameBoard.NUM_COLS);
		int randY = random.nextInt(GameBoard.NUM_ROWS);
		
		this.setRow(randY);
		this.setCol(randX);
		
		display();
	}
	
	public void safeMove() {
		int randX = random.nextInt(GameBoard.NUM_COLS);
		int randY = random.nextInt(GameBoard.NUM_ROWS);
		
		if (safeClear(randY, randX) == true) {
			this.setRow(randY);
			this.setCol(randX);
		}
		display();
	}
	
	public boolean safeClear(int row, int col) {
		for (int r = row - 1; r <= row + 1; r++) {
			for (int c = col - 1; c <= col + 1; c++) {
				if (GameBoard.isValid(r, c) == true && GameBoard.isClear(r, c) == true) {
					return true;
				}
			}
		}
		return false;
	}
}
