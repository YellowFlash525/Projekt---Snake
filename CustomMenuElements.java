import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class CustomMenuElements extends Parent {
	
	//----------------------------------tworzenie custom menu-----------------------------------
	public CustomMenuElements() {

		// tworzymy menu
		VBox menu0 = new VBox(10);
		menu0.setTranslateX(100);
		menu0.setTranslateY(200);

		// tworzymy submenu
		VBox menu1 = new VBox(10);
		menu1.setTranslateX(100);
		menu1.setTranslateY(200);

		final int offset = 400;
		menu1.setTranslateX(offset); // powoduje, ¿e oba menu nie wyœwietlaj¹ siê w tym samym momencie
		
		// przycisk do nowej gry
		
		CustomMenuButton newGame = new CustomMenuButton("New Game");
		newGame.setOnMouseClicked(event -> {
			new Snake();
		});

		// przycisk do opcji
		CustomMenuButton options = new CustomMenuButton("Options");
		options.setOnMouseClicked(event -> {
			// przejœcie do instrukcji gry
			getChildren().add(menu1);
			
			
			TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.25), menu0);
			tt1.setToX(menu0.getTranslateX() - offset);
			
			TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.5), menu1);
			tt2.setToX(menu0.getTranslateX());
			
			tt1.play();
			tt2.play();
			
			tt1.setOnFinished(evt -> {
				getChildren().remove(menu0);
			});
		});

		// przycisk do wyjœcia z gry
		CustomMenuButton exitGame = new CustomMenuButton("Exit");
		exitGame.setOnMouseClicked(event -> {
			System.exit(0);
		});
		
		CustomMenuButton sound1 = new CustomMenuButton("SOUND: ON");
		sound1.setOnMouseClicked(event -> {
			Sound.muza3.play();
		});
		
		CustomMenuButton sound2 = new CustomMenuButton("SOUND: OFF");
		sound2.setOnMouseClicked(event -> {
			Sound.muza3.stop();
		});
		
		CustomMenuButton back = new CustomMenuButton("Back");
		back.setOnMouseClicked(event -> {
			getChildren().add(menu0);
			
			TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.25), menu1);
			tt1.setToX(menu1.getTranslateX() + offset);
			
			TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.5), menu0);
			tt2.setToX(menu1.getTranslateX());
			
			tt1.play();
			tt2.play();
			
			tt1.setOnFinished(evt ->{
				getChildren().remove(menu1);
			});
		});

		menu0.getChildren().addAll(newGame, options, exitGame);
		menu1.getChildren().addAll(sound1, sound2, back);

		Rectangle rg = new Rectangle(700, 500);
		rg.setFill(Color.GREY);
		rg.setOpacity(0.4);
		
		getChildren().addAll(rg, menu0);
	}

}