public class Card {
    public Suit suit;
    public Number value;
    public Card(int value, int suit){
        this.suit = Suit.label(suit);
        this. value = Number.label(value);
    }
    public static String shorted(Card c){
        return c!=null?c.shorthand():"__";
    }

    @Override
    public String toString() {
        return value + " of "+suit+"s";
    }

    public String shorthand() {
        return value.getShorthand()+suit.getShorthand();
    }
}
