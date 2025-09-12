import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CardComponent extends Component {
  private final CardModel cardModel;
  private final BooleanProperty isFaceUp = new SimpleBooleanProperty();

  public CardComponent(CardModel cardModel, boolean isFaceUp) {
    this.cardModel = cardModel;
    this.isFaceUp.set(isFaceUp);
  }

  public BooleanProperty isFaceUpProperty() {
    return isFaceUp;
  }

  public CardModel getCardModel() {
    return cardModel;
  }

  @Override
  public boolean isComponentInjectionRequired() {
    return false;
  }

}
