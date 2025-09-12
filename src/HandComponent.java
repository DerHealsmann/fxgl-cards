import com.almasb.fxgl.entity.Entity;

public class HandComponent extends CardContainerComponent {
  public static final int REGION_WIDTH = 350;
  public static final int REGION_HEIGHT = 175;


  @Override
  public void layoutCards() {
    super.layoutCards();
    if (cards.isEmpty()) {
      return;
    }

    alignCards();
  }

  private void alignCards() {
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
}
