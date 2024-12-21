import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    public Deck(){
        deck = new ArrayList<Card>();
        for(int i = 1 ; i<=4;i++){
            for(int j = 1; j<=13;j++){
                deck.add(new Card(j,i));
            }
        }
        shuffle();
    }
    public Card draw(){
        Card top = deck.get(0);
        deck.remove(0);
        return top;
    }
    public void shuffle(){
        Collections.shuffle(deck);
    }
    public ArrayList<Card> drawX(int X){
        ArrayList<Card> temp = new ArrayList<Card>();
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
