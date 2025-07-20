import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import java.util.Collections;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class DeckComponent extends Component {
  private final SimpleListProperty<CardModel> deck;
  private boolean spawnCardsFaceUp;

  public DeckComponent (DeckModel deckModel) {
    this.deck = new SimpleListProperty<>(FXCollections.observableList(deckModel.getCards()));
    this.spawnCardsFaceUp = deckModel.isSpawnCardsFaceUp();
  }

  @Override
  public void onAdded() {
    super.onAdded();
    shuffle();
    getEntity().getViewComponent().addOnClickHandler(e -> {
      var newCard = spawnCard(deck.removeFirst());
      var handComponent = FXGL.getGameWorld().getEntitiesByComponent(HandComponent.class).getFirst().getComponent(HandComponent.class);
      handComponent.addCard(newCard);
    });
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

  private Entity spawnCard(CardModel card) {
    var cardData = new SpawnData(getAppWidth() / 2d, getAppHeight() / 2d);
    cardData.put(SpawnDataKeys.MODEL, card);
    cardData.put(SpawnDataKeys.IS_FACE_UP, spawnCardsFaceUp);
    return spawn(SpawnKeys.CARD, cardData);
  }

}
