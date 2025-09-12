import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import java.util.ArrayList;
import java.util.List;

public class CardContainerComponent extends Component {
  protected List<Entity> cards = new ArrayList<>();
  private static final int CARD_SPACING = 28;
  @Override
  public void onAdded() {
    super.onAdded();
    layoutCards();
  }

  public void layoutCards() {
    if (cards.isEmpty()) {
      return;
    }
    stackCards();
  }

  private void stackCards() {
    var regionTopLeft = getEntity().getPosition();
    for (int i = 0; i < cards.size(); i++) {
      var currentCard = cards.get(i);
      currentCard.setPosition(regionTopLeft.getX() + i * CARD_SPACING, regionTopLeft.getY());
      currentCard.setZIndex(40 + i);
    }
  }

  public void addCard(Entity card) {
    cards.add(card);
    layoutCards();
    System.out.println(cards.size());
  }

  public void removeCard(Entity card) {
    cards.remove(card);
    layoutCards();
  }

  public List<Entity> getCards() {
    return cards;
  }
}
