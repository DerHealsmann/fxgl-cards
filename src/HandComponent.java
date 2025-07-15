import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class HandComponent extends Component {
  private final int regionWidth = 350;
  private final int regionHeight = 175;
  private List<Entity> cards = new ArrayList<>();
  private final int CARD_SPACING = 28;

  public HandComponent() {
    cards.addAll(List.of(
        spawnCard(new CardModel(CardModel.Rank.ACE, CardModel.Suit.CLUBS)),
        spawnCard(new CardModel(CardModel.Rank.KING, CardModel.Suit.DIAMONDS)),
        spawnCard(new CardModel(CardModel.Rank.THREE, CardModel.Suit.HEARTS)))
    );
  }

  @Override
  public void onAdded() {
    super.onAdded();
    layoutCards();
  }

  private Entity spawnCard(CardModel card) {
    var cardData = new SpawnData(getAppWidth() / 2d, getAppHeight() / 2d);
    cardData.put(SpawnDataKeys.MODEL, card);
    cardData.put(SpawnDataKeys.IS_FACE_UP, true);
    return spawn(SpawnKeys.CARD, cardData);
  }

  private void layoutCards() {
    var handPos = getEntity().getPosition().add(regionWidth / 2d, regionHeight / 2d);
    for (int i = 0; i < cards.size(); i++) {
      var currentCard = cards.get(i);
      var cardPos = handPos.subtract(CardView.CARD_WIDTH / 2d, CardView.CARD_HEIGHT / 2d);
      currentCard.setPosition(cardPos.getX() + i * CARD_SPACING, cardPos.getY());
      currentCard.setZIndex(40 + i);
    }
  }

  public int getRegionHeight() {
    return regionHeight;
  }

  public int getRegionWidth() {
    return regionWidth;
  }

}
