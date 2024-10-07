import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ButtonBoard extends GridPane {
	
	public static final int BUTTON_WIDTH = 30;
	private GameBoard gameBoard;
	private Player player;
	
	private Button buttonLeft;
	private Button buttonUp;
	private Button buttonRight;
	private Button buttonDown;
	private Button buttonLeftDown;
	private Button buttonLeftUp;         
	private Button buttonRightUp;
	private Button buttonRightDown;
	private Button buttonTeleport;
	private Button buttonSafeTeleport;
		
	public ButtonBoard(GameBoard gameBoard) {
		
		this.gameBoard = gameBoard;
		
		buttonLeft = new Button("Left");
		
		buttonUp = new Button("Up");
		buttonUp.setPrefSize(100, 20);
		
		buttonRight = new Button("Right");
		
		buttonDown = new Button("Down");
		buttonDown.setPrefSize(100, 20);
		
		buttonLeftDown = new Button("L-D");
		buttonLeftUp = new Button("L-U");
		
		buttonRightUp = new Button("R-U");
		buttonRightUp.setPrefWidth(45);
		
		buttonRightDown = new Button("R-D");
		buttonRightDown.setPrefWidth(45);
		
		buttonTeleport = new Button("Teleport");
		buttonTeleport.setPrefSize(100, 20);
		
		buttonSafeTeleport = new Button("SafeTeleport");
		buttonSafeTeleport.setPrefSize(110, 20);

		setHgap(5);
		setVgap(5);
		setAlignment(Pos.CENTER);
		
		add(buttonLeft, 0, 1);
		add(buttonUp, 1, 0);
		add(buttonRight, 2, 1);
		add(buttonDown, 1, 2);
		add(buttonLeftDown, 0, 2);
		add(buttonLeftUp, 0, 0);
		add(buttonRightUp, 2, 0);
		add(buttonRightDown, 2, 2);
		add(buttonTeleport, 1, 1);
		add(buttonSafeTeleport, 1, 3);
		
		player = gameBoard.getPlayer();
		
		buttonAction();
	}
	
	public void buttonAction()
	{
		buttonLeft.setFocusTraversable(false);
		buttonUp.setFocusTraversable(false);
		buttonRight.setFocusTraversable(false);
		buttonDown.setFocusTraversable(false);
		buttonLeftDown.setFocusTraversable(false);
		buttonLeftUp.setFocusTraversable(false);
		buttonRightUp.setFocusTraversable(false);
		buttonRightDown.setFocusTraversable(false);
		buttonTeleport.setFocusTraversable(false);
		buttonSafeTeleport.setFocusTraversable(false);
		
		buttonLeft.setOnAction(e -> {
			if (GameBoard.gameContinued == true) {
				gameBoard.update(0, -1);
			}
		});
		
		buttonUp.setOnAction(e -> {
			if (GameBoard.gameContinued == true) {
				gameBoard.update(-1, 0);
			}
		});
		
		buttonRight.setOnAction(e -> {
			if (GameBoard.gameContinued == true) {
				gameBoard.update(0,  1);
			}
		});
		
		buttonDown.setOnAction(e -> {
			if (GameBoard.gameContinued == true) {
				gameBoard.update(1, 0);
			}
		});
		
		buttonLeftDown.setOnAction(e -> {
			if (GameBoard.gameContinued == true) {
				gameBoard.update(1, -1);
			}
		});
		
		buttonLeftUp.setOnAction(e -> {
			if (GameBoard.gameContinued == true) {
				gameBoard.update(-1, -1);
			}
		});
		
		buttonRightUp.setOnAction(e -> {
			if (GameBoard.gameContinued == true) {
				gameBoard.update(-1, 1);
			}
		});
		
		buttonRightDown.setOnAction(e -> {
			if (GameBoard.gameContinued == true) {
				gameBoard.update(1, 1);
			}
		});
		
		buttonTeleport.setOnAction(e -> {
			if (GameBoard.gameContinued == true) {
				player.randomMove();
				gameBoard.updateMoveRobot();
			}
		});
		
		buttonSafeTeleport.setOnAction(e -> { 
			if (GameBoard.gameContinued == true) {
				player.safeMove();
				gameBoard.updateMoveRobot();
			}
		});
	}
}
