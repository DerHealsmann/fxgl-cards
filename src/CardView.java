import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CardView extends StackPane {
  public CardView(CardModel cardModel) {
    final var labelText = cardModel.toString();
    final var paint = cardModel.isRed() ? Color.RED : Color.BLACK;

    getChildren().addAll(
        createCardBackground(),
        createCardLabel(labelText, paint, Pos.TOP_LEFT, 0d),
        createCardLabel(labelText, paint, Pos.BOTTOM_RIGHT, 180d)
    );
  }

  private Node createCardBackground() {
    var cardBackground = new Rectangle(88, 112);
    cardBackground.setFill(Color.GHOSTWHITE);
    return cardBackground;
  }

  private Node createCardLabel(String cardText, Color color, Pos position, double rotation) {
    var label = new Text(cardText);
    label.setFill(color);
    StackPane.setAlignment(label, position);
    StackPane.setMargin(label, new Insets(10));
    label.setRotate(rotation);
    return label;
  }
}
