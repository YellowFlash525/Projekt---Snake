import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CustomMenu extends Application {

	private CustomMenuElements CMElements;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane root = new Pane();
		root.setPrefSize(600, 400);
	

		InputStream in = Files.newInputStream(Paths.get("res/SnakeMenu.png"));
		Image img = new Image(in);
		in.close();

		ImageView imgView = new ImageView(img);
		imgView.setFitHeight(400);
		imgView.setFitWidth(600);
		
		CustomMenuTitle title = new CustomMenuTitle("S N A K E");
		title.setTranslateX(75);
		title.setTranslateY(120);

		CMElements = new CustomMenuElements();
		CMElements.setVisible(true);
		
		root.getChildren().addAll(imgView, title, CMElements);

		Scene scene = new Scene(root);
		
		Sound.muza3.play();

		//----------------------------------akcja przy wcisnieciu ESC-------------------------------
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				if (!CMElements.isVisible()) {
					FadeTransition ft = new FadeTransition(Duration.seconds(0.5), CMElements);
					ft.setFromValue(0);
					ft.setToValue(1);

					CMElements.setVisible(true);
					ft.play();
				} else {
					FadeTransition ft = new FadeTransition(Duration.seconds(0.5), CMElements);
					ft.setFromValue(1);
					ft.setToValue(0);
					ft.setOnFinished(evt -> CMElements.setVisible(false));
					ft.play();
				}
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
