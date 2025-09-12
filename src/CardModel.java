import java.util.Objects;

public class CardModel {
    private final Rank rank;
    private final Suit suit;

    public enum Rank {
        ACE("A"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("10"),
        JACK("J"),
        QUEEN("Q"),
        KING("K");

        Rank(String displayText) {
            this.displayText = displayText;
        }

        private final String displayText;

        @Override
        public String toString() {
            return displayText;
        }
    }

    public enum Suit {
        HEARTS('♥', true),
        DIAMONDS('♦', true),
        SPADES('♠', false),
        CLUBS('♣', false);

        private final char displayText;
        private final boolean isRed;

        Suit(char displayText, boolean isRed) {
            this.displayText = displayText;
            this.isRed = isRed;
        }

        @Override
        public String toString() {
            return String.valueOf(displayText);
        }
    }

    public CardModel(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isRed() {
        return this.suit.isRed;
    }

    @Override
    public String toString() {
        return rank.toString() + " " + suit.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CardModel cardModel = (CardModel) o;
        return rank == cardModel.rank && suit == cardModel.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    public Rank getRank() { return rank; }

    public Suit getSuit() { return suit; }
}