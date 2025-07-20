import java.util.ArrayList;
import java.util.List;

public class DeckModel {
  private final List<CardModel> deck = new ArrayList<>();
  private static final List<CardModel.Rank> RANKS_ORDERED = List.of(
      CardModel.Rank.ACE,
      CardModel.Rank.KING,
      CardModel.Rank.QUEEN,
      CardModel.Rank.JACK,
      CardModel.Rank.TEN,
      CardModel.Rank.NINE,
      CardModel.Rank.EIGHT,
      CardModel.Rank.SEVEN,
      CardModel.Rank.SIX,
      CardModel.Rank.FIVE,
      CardModel.Rank.FOUR,
      CardModel.Rank.THREE,
      CardModel.Rank.TWO
  );
  private static final List<CardModel.Suit> SUITS_ORDERED = List.of(
      CardModel.Suit.HEARTS,
      CardModel.Suit.DIAMONDS,
      CardModel.Suit.SPADES,
      CardModel.Suit.CLUBS
  );
  private final boolean spawnCardsFaceUp = true;

  public DeckModel() {
    fillDeck();
  }

  public List<CardModel> getCards() {
    return deck;
  }

  private void fillDeck() {
    deck.clear();
    for (CardModel.Suit suit : SUITS_ORDERED) {
      for (CardModel.Rank rank : RANKS_ORDERED) {
        var newCard = new CardModel(rank, suit);
        deck.add(newCard);
      }
    }
  }

  public boolean isSpawnCardsFaceUp() {
    return spawnCardsFaceUp;
  }
}