public class Test {
    public static void main(String[] args){
        Deck deck = new Deck();
        System.out.println(deck);
        System.out.println(deck.shorthand());
        deck.drawX(5);
        System.out.println(deck.shorthand());
    }
}
