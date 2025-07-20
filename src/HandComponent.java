import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import java.util.ArrayList;
import java.util.List;

public class HandComponent extends Component {
  public static final int REGION_WIDTH = 350;
  public static final int REGION_HEIGHT = 175;
  private final List<Entity> cards = new ArrayList<>();
  private static final int CARD_SPACING = 28;

  @Override
  public void onAdded() {
    super.onAdded();
    layoutCards();
  }

  private void layoutCards() {
    if (cards.isEmpty()) {
      return;
    }
    var handPos = getEntity().getPosition();
    for (int i = 0; i < cards.size(); i++) {
      var currentCard = cards.get(i);
      var cardPos = handPos;
      currentCard.setPosition(cardPos.getX() + i * CARD_SPACING, cardPos.getY());
      currentCard.setZIndex(40 + i);
    }
    // TODO: use getRightX() instead (when it works)
    //       also assumes only left-right layouts;
    var firstCardX = cards.getFirst().getX();
    var lastCardX = cards.getLast().getX() + CardView.CARD_WIDTH;
    var handWidth = lastCardX - firstCardX;
    var handHeight = CardView.CARD_HEIGHT;

    for (Entity currentCard : cards) {
      currentCard.translateX((REGION_WIDTH / 2d) - (handWidth / 2d));
      currentCard.translateY((REGION_HEIGHT / 2d) - (handHeight / 2d));
    }
  }

  public void addCard(Entity card) {
    cards.add(card);
    layoutCards();
  }
}
