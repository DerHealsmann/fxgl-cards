import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import java.util.ArrayList;
import java.util.List;

public class CardContainerComponent extends Component {
  protected List<Entity> cards = new ArrayList<>();
  private static final int CARD_SPACING = 28;

  private final int REGION_WIDTH;
  private final int REGION_HEIGHT;
  private final AlignmentMode ALIGNMENT;

  CardContainerComponent(int width, int height, AlignmentMode alignment) {
    REGION_WIDTH = width;
    REGION_HEIGHT = height;
    ALIGNMENT = alignment;
  }

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
    alignCards();
  }

  private void stackCards() {
    var regionTopLeft = getEntity().getPosition();
    for (int i = 0; i < cards.size(); i++) {
      var currentCard = cards.get(i);
      currentCard.setPosition(regionTopLeft.getX() + i * CARD_SPACING, regionTopLeft.getY());
      currentCard.setZIndex(40 + i);
    }
  }

  private void alignCards() {
    switch (ALIGNMENT) {
      case TOP_LEFT -> {} // default layout is top left
      case CENTER -> centreAlignCards();
    }
  }

  private void centreAlignCards() {
    // TODO: use getRightX() instead (when it works)
    //       also assumes only left-right layouts;
    var firstCardX = cards.getFirst().getX();
    var lastCardX = cards.getLast().getX() + CardView.CARD_WIDTH;
    var allCardsWidth = lastCardX - firstCardX;
    var allCardsHeight = CardView.CARD_HEIGHT;

    for (Entity currentCard : cards) {
      currentCard.translateX((REGION_WIDTH / 2d) - (allCardsWidth / 2d));
      currentCard.translateY((REGION_HEIGHT / 2d) - (allCardsHeight / 2d));
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
