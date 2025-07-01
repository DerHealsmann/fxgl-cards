import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.Collections;

public class DeckComponent extends Component {
  private SimpleListProperty<CardModel> deck;

  public DeckComponent (DeckModel deckModel) {
    this.deck = new SimpleListProperty<>(FXCollections.observableList(deckModel.getCards()));
  }

  public CardModel getTopCard() {
    return deck.getFirst();
  }

  public void shuffle() {
    Collections.shuffle(deck);
  }

  public int getSize() {
    return deck.size();
  }

  public ReadOnlyBooleanProperty isEmptyProperty() {
    return deck.emptyProperty();
  }

}
