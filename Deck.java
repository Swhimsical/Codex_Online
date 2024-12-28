import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    private LinkedList<Card> deck;
    public Deck(boolean full){
        deck = new LinkedList<>();
        if(full) {
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 13; j++) {
                    deck.add(new Card(j, i));
                }
            }
            shuffle();
        }
    }
    public void add(Card c, boolean top){
        if(top){
            deck.push(c);
        } else {
            deck.add(c);
        }
    }
    public void reset(Deck d){
        deck = (LinkedList<Card>) d.deck.clone();
        shuffle();

    }
    public Card top(){
        if(deck.isEmpty()){
            return null;
        }
        return deck.get(0);
    }
    public Card draw(){
        return deck.pop();
    }
    public void shuffle(){
        Collections.shuffle(deck);
    }
    public ArrayList<Card> drawX(int X){
        ArrayList<Card> temp = new ArrayList<>();
        for(int i = 0;i<X;i++){
            if(deck.isEmpty()){
                return temp;
            }
            temp.add(draw());
        }
        return temp;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                '}';
    }

    public String shorthand() {
        StringBuilder str = new StringBuilder(214);
        str.append('(');
        for (Card c:
             deck) {
            str.append(c.shorthand());
            str.append(", ");
        }
        int l=str.length();
        str.delete(l-2,l);
        str.append(')');
        return str.toString();
    }
}
