import javafx.scene.paint.Color;

public class Robot extends GameObject {
	
	public Robot(int row, int col) 
	{
		super(row, col);		
		setFill(Color.BLUE);
	}
	
	public void move(int dRow, int dCol)
	{	
		if (GameBoard.isValid(this.row + dRow, this.col + dCol)) {
			this.row += dRow;
			this.col += dCol;
		}
		
		display();
	}
}
