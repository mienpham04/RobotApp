import javafx.scene.layout.BorderPane;

public class GamePlay extends BorderPane
{
	GameBoard gameBoard;
	
	public GamePlay()
	{
		gameBoard = new GameBoard();
		ButtonBoard buttonBoard = new ButtonBoard(gameBoard);
		ScoreBoard scoreBoard = new ScoreBoard(gameBoard);
		
		setRight(buttonBoard);
		setTop(scoreBoard);
		setCenter(gameBoard);
	}
	
	public GameBoard getGameBoard()
	{
		return gameBoard;
	}
}
