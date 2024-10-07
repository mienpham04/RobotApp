import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameBoard extends Pane
{
	public static final int SIZE = 30;
	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;
	public static final int NUM_ROBOTS = 5;

	public static final int NUM_ROWS = HEIGHT / SIZE;
	public static final int NUM_COLS = WIDTH / SIZE;

	public static int numRubble;
	public int score;

	private static Random random = new Random();

	private static GameObject[][] board;

	public ArrayList<Robot> robots;
	public ArrayList<Rubble> rubbles;

	public static boolean gameContinued = true;

	public Player player;
	public Rubble rubble;

	public Button restartButton;
	public Label label;
	
	public GameBoard()
	{
		setWidth(WIDTH);
		setHeight(HEIGHT);

		robots = new ArrayList<>();

		board = new GameObject[NUM_ROWS][NUM_COLS];

		setPadding(new Insets(5, 5, 5, 5));

		drawBoard();

		player = new Player();
		board[player.getRow()][player.getCol()] = player;
		getChildren().add(player);

		rubbles = new ArrayList<>();

		restartButton = new Button("Restart?");
		restartButton.setLayoutX(WIDTH/2);
		restartButton.setLayoutY(HEIGHT/2  - SIZE * 3);
		restartButton.setPrefSize(100, 60);

		restartButton.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Restart Game");
			alert.setHeaderText("Are you sure you want to restart the game?");
			String score = "Your score: " + getScore();
			alert.setContentText(score);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				reset();
			}
		});
		
		getChildren().add(restartButton);
		restartButton.setVisible(false);

		addRobot();
		action();
	}
	
	public int getScore() {
		return numRubble * 10;
	}

	public void drawBoard() {
		for (int r = 0; r < NUM_ROWS; r++) {
			for (int c = 0; c < NUM_COLS; c++) {
				Rectangle rec = new Rectangle(SIZE, SIZE);

				if ((r + c) % 2 == 0) {
					rec.setFill(Color.GRAY);
				}

				else {
					rec.setFill(Color.WHITE);
				}

				rec.setX(SIZE * c);
				rec.setY(SIZE * r);
				getChildren().add(rec);
			}
		}
	}

	public static int getNumRubble() {
		return numRubble;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Robot> getRobots() {
		return robots;
	}

	public static boolean isClear(int r, int c)
	{
		if (board[r][c] == null) {
			return true;
		}

		return false;
	}

	public static boolean isValid(int row, int col)
	{
		if (row >= 0 && row < NUM_ROWS && col >= 0 && col < NUM_COLS) {
			return true;
		}
		return false;
	}

	public void addRobot()
	{
		int num = 0;

		while (num < NUM_ROBOTS) {
			int row = random.nextInt(GameBoard.NUM_ROWS);
			int col = random.nextInt(GameBoard.NUM_COLS);

			if (isClear(row, col) == true) {
				Robot robot = new Robot(row, col);
				board[row][col] = robot;
				num++;

				getChildren().add(robot);
				robots.add(robot);
			}
		}
	}

	public void updateMoveRobot()
	{		
		for (int i = 0; i < robots.size(); i++) {
			Robot robot = robots.get(i);

			int initRow = robot.getRow();
			int initCol = robot.getCol();

			if (player.getRow() > robot.getRow() && player.getCol() > robot.getCol()) {
				robot.move(1, 1);
			}

			else if (player.getRow() == robot.getRow() && player.getCol() > robot.getCol()) {
				robot.move(0, 1);
			}

			else if (robot.getRow() > player.getRow() && player.getCol() > robot.getCol()) {
				robot.move(-1, 1);
			}

			else if (robot.getCol() == player.getCol() && robot.getRow() > player.getRow()) {
				robot.move(-1, 0);
			}

			else if (robot.getRow() > player.getRow() && robot.getCol() > player.getCol()) {
				robot.move(-1, -1);
			}

			else if (robot.getRow() == player.getRow() && robot.getCol() > player.getCol()) {
				robot.move(0, -1);
			}

			else if (robot.getCol() > player.getCol() && robot.getRow() < player.getRow()) {
				robot.move(1, -1);
			}

			else if (robot.getCol() == player.getCol() && robot.getRow() < player.getRow()) {
				robot.move(1, 0);
			}

			if (addRubble() == true) {
				i = i - 1;
			}

			else if (robotCollision() == true) {
				robots.remove(i);
			}

			boardUpdate(initRow, initCol, robot);

			if (playerLose() == true || playerCollision() == true || robots.size() == 0) {
				gameContinued = false;
				label = new Label();
				label.setLayoutX(WIDTH/2);
				label.setLayoutY(HEIGHT/2);
				label.setFont(Font.font(28));

				if (robots.size() == 0) {
					label.setText("You win");
				}

				else {
					label.setText("You lose");
				}
				restartButton.setVisible(true);				
				getChildren().add(label); 
			}	
		}
	}

	public void update(int row, int col)
	{
		int numRow = player.getRow() + row;
		int numCol = player.getCol() + col;

		if (isValid(numRow, numCol) && gameContinued == true) {	
			int initRow = player.getRow();
			int initCol = player.getCol();

			player.move(row, col);
			updateMoveRobot();	

			boardUpdate(initRow, initCol, player);	
		}
	}

	public void action() {
		setOnKeyPressed(e -> {
			if (gameContinued == true) {
				switch (e.getCode()) {
				case UP: update(-1, 0); break;
				case DOWN: update(1, 0); break;
				case LEFT: update(0, -1); break;
				case RIGHT: update(0, 1); break;

				case Q: update(-1, -1); break;
				case E: update(-1, 1); break;
				case Z: update(1, -1); break;
				case C: update(1, 1); break;

				case B:
					player.randomMove();
					updateMoveRobot();
					break;

				case V:
					player.safeMove();
					updateMoveRobot();
					break;

				default: break;
				}

				e.consume();
			}
		});
	}

	public void boardUpdate(int initRow, int initCol, GameObject gameObject) {		
		board[initRow][initCol] = null;
		board[gameObject.getRow()][gameObject.getCol()] = gameObject;
	}

	public boolean addRubble() {
		for (int i = 0; i < robots.size(); i++) {
			Robot robot1 = robots.get(i);

			for (int j = i + 1; j < robots.size(); j++) {
				Robot robot2 = robots.get(j);

				if (robot1.getRow() == robot2.getRow() && robot1.getCol() == robot2.getCol()) {
					Rubble rubble = new Rubble(robot1.getRow(), robot1.getCol());
					robots.remove(robot1);
					robots.remove(robot2);
					getChildren().add(rubble);
					rubbles.add(rubble);
					
					numRubble = numRubble + 1;
					ScoreBoard.updateLabel();

					return true;
				}
			}
		}
		return false;
	}

	public boolean playerLose() {
		for (Robot robot: robots) {
			if (player.getRow() == robot.getRow() && player.getCol() == robot.getCol()) {
				return true;
			}
		}
		return false;
	}

	public boolean playerCollision() {
		for (Rubble rubble: rubbles) {

			if (player.getRow() == rubble.getRow() && player.getCol() == rubble.getCol()) {
				return true;
			}
		}
		return false;
	}

	public boolean robotCollision() {
		for (int i = 0; i < rubbles.size(); i++) {
			Rubble rubble = rubbles.get(i);

			for (int j = 0; j < robots.size(); j++) {
				Robot robot = robots.get(j);

				if (robot.getRow() == rubble.getRow() && robot.getCol() == rubble.getCol()) {

					j = j - 1;
					return true;
				}
			}
		}
		return false;
	}

	private void reset() {
		robots.clear();
		rubbles.clear();
		
		getChildren().removeAll(robots);
		getChildren().removeAll(rubbles);
		getChildren().remove(player);
		getChildren().clear();
		
		gameContinued = true;

		numRubble = 0;
		ScoreBoard.updateLabel();
		
		label.setText("");

		drawBoard();

		player = new Player();
		getChildren().add(player);
		
		addRobot();		

		getChildren().add(restartButton);
		restartButton.setVisible(false);
		
		requestFocus();
	}
}
