public class DeckView extends CardView {
  public DeckView(DeckComponent deckComponent) {
    super(new CardComponent(new CardModel(CardModel.Rank.ACE, CardModel.Suit.HEARTS), false));
    visibleProperty().bind(deckComponent.isEmptyProperty().not());
  }
}
