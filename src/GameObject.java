import javafx.scene.shape.Circle;

public class GameObject extends Circle
{
	protected int row;
	protected int col;

	public GameObject(int row, int col)
	{
		this.row = row;
		this.col = col;
		setRadius(GameBoard.SIZE / 2);
		display();
	}

	public void display()
	{
		setCenterX((col * GameBoard.SIZE) + (GameBoard.SIZE / 2));
		setCenterY((row * GameBoard.SIZE) + (GameBoard.SIZE / 2));
	}

	public int getRow()
	{
		return row;
	}

	public int getCol()
	{
		return col;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public void setCol(int col)
	{
		this.col = col;
	}
}
