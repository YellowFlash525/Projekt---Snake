import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// tworzymy grafikê wszystkich przycisków w custom menu
	public class CustomMenuButton extends StackPane {

		private Text text;

		public CustomMenuButton(String name) {
			text = new Text(name);
			text.getFont();
			text.setFont(Font.font(28)); // nadajemy zmiennej text rozmiar 28px
			text.setFill(Color.WHITE); // napisy maj¹ byæ koloru bia³ego

			Rectangle rg = new Rectangle(200, 30); // tworzymy prostok¹t
			rg.setOpacity(0.6);
			rg.setFill(Color.BLACK); // nadajemy mu kolor czarny

			rg.setEffect(new GaussianBlur(3.5)); // dodajemy do ka¿dego
													// prostok¹ta opcje smugi

			setAlignment(Pos.CENTER_LEFT); // umiejscowienie wszystkich
											// atrybutów menu
			
			getChildren().addAll(rg, text); // dodanie do rect odpowiednich
											// tytu³ów

			// odpowiaa za to co siê dzieje, gdy kursor myszki najedzie na jakiœ
			// prostok¹t
			setOnMouseEntered(event -> {
				rg.setWidth(220.0);
				rg.setHeight(35.0);
				text.setTranslateX(10);
				rg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			});

			// odwrotne dzia³anie do setOnMauseEntered
			setOnMouseExited(event -> {
				rg.setWidth(200.0);
				rg.setHeight(30.0);
				text.setTranslateX(0);
				rg.setFill(Color.BLACK);
				text.setFill(Color.WHITE);
			});

			DropShadow drop = new DropShadow(50, Color.WHITE); // poœwiata na przyciœnietym przyciskiem
			drop.setInput(new Glow());

			setOnMousePressed(event -> setEffect(drop)); // w momencie gdy przyciskamy przycisk zapala siê poœwiata
			setOnMouseReleased(event -> setEffect(null)); // w momencie gdy puszczamy poœwiata znika
		}
	}