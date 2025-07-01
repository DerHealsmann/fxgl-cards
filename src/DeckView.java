public class DeckView extends CardView {
  public DeckView(DeckComponent deckComponent) {
    super(new CardModel(CardModel.Rank.ACE, CardModel.Suit.SPADES), false);
    visibleProperty().bind(deckComponent.isEmptyProperty().not());
  }
}
