import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CardView extends StackPane {
  private final BooleanProperty isFaceUp = new SimpleBooleanProperty();

  public CardView(CardModel cardModel, boolean isCardFaceUp) {
    this.isFaceUp.set(isCardFaceUp);
    final var labelText = cardModel.toString();
    final var paint = cardModel.isRed() ? Color.RED : Color.BLACK;
    final var topLabel = createCardLabel(labelText, paint, Pos.TOP_LEFT, 0d);
    final var bottomLabel = createCardLabel(labelText, paint, Pos.BOTTOM_RIGHT, 180d);
    topLabel.visibleProperty().bind(isFaceUp);
    bottomLabel.visibleProperty().bind(isFaceUp);
    getChildren().addAll(
        createCardBackground(),
        topLabel,
        bottomLabel
    );
  }

  private Node createCardBackground() {
    var cardBackground = new Rectangle(88, 112);
    cardBackground.fillProperty().bind(
        Bindings.createObjectBinding(()-> isFaceUp.get() ? Color.GHOSTWHITE : Color.TOMATO, isFaceUp)
    );
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
