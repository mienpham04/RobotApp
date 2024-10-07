import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ScoreBoard extends Pane {
	
	public static Label label;
	public static String string;
	
	public ScoreBoard(GameBoard board) {
		string = "Rubble created: ";
		label = new Label(string + GameBoard.getNumRubble());
		
		label.setAlignment(Pos.CENTER);
		label.setFont(Font.font(20));
		
		getChildren().add(label);
		
	}
	
	public Label getLabel() {
		return label;
	}
	
	public void setLabel(Label label) {
		ScoreBoard.label = label;
	}
	
	public static void updateLabel() {
		label.setText(string + GameBoard.getNumRubble());
	}
}
