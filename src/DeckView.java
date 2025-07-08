public class DeckView extends CardView {
  public DeckView(DeckComponent deckComponent) {
    super(new CardComponent(false));
    visibleProperty().bind(deckComponent.isEmptyProperty().not());
  }
}
