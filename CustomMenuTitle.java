import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class CustomMenuTitle extends StackPane{
		public CustomMenuTitle(String name){
			Rectangle bg = new Rectangle(250,50);
			bg.setStroke(Color.WHITE);
			bg.setStrokeWidth(2);
			bg.setFill(null);
			
			Text text = new Text(name);
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.BOLD, 55));
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg, text);
			
		}
	}