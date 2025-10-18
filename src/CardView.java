import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CardView extends StackPane {
  private final BooleanProperty isFaceUp = new SimpleBooleanProperty();

  private static final int CARD_WIDTH = 88;
  private static final int CARD_HEIGHT = 112;

  public CardView(CardComponent component) {
    this.isFaceUp.bind(component.isFaceUpProperty());
    getChildren().add(createCardBackground());
    if (component.getCardModel() == null) {
      return;
    }
    final var labelText = component.getCardModel().toString();
    final var paint = component.getCardModel().isRed() ? Color.RED : Color.BLACK;
    final var topLabel = createCardLabel(labelText, paint, Pos.TOP_LEFT, 0d);
    final var bottomLabel = createCardLabel(labelText, paint, Pos.BOTTOM_RIGHT, 180d);
    topLabel.visibleProperty().bind(isFaceUp);
    bottomLabel.visibleProperty().bind(isFaceUp);
    getChildren().addAll(
      topLabel,
      bottomLabel
    );
  }

  private Node createCardBackground() {
    var cardBackground = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
    cardBackground.setArcWidth(12.5d);
    cardBackground.setArcHeight(12.5d);

    //test shadow
    DropShadow ds = new DropShadow(2, -.7, 2, Color.DARKGRAY);
    cardBackground.setEffect(ds);

    cardBackground.fillProperty().bind(
        Bindings.createObjectBinding(() -> isFaceUp.get() ? Color.GHOSTWHITE : Color.TOMATO, isFaceUp)
    );
    return cardBackground;
  }

  private Node createCardLabel(String cardText, Color color, Pos position, double rotation) {
    //TODO: sort alignment of rank/suit? margins?
    var label = new Text(cardText);
    label.setFill(color);
    StackPane.setAlignment(label, position);
    StackPane.setMargin(label, new Insets(5));
    label.setRotate(rotation);
    return label;
  }

  public int getCardWidth() {
    return CARD_WIDTH;
  }

  public int getCardHeight() {
    return CARD_HEIGHT;
  }
}
