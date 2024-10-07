import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			GamePlay gamePlay = new GamePlay();
			
			Scene scene = new Scene(gamePlay);
			primaryStage.setTitle("Robot Game");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			gamePlay.getGameBoard().requestFocus();			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
