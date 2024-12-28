import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private final Card[] field;
    public Player (ArrayList<Card> start){
        field = new Card[7];
        field[0] = start.get(0);
        field[4] = start.get(1);
        field[2] = start.get(2);
        System.out.println(Arrays.toString(field));
    }

    public Card place(Card c, int p){
        Card t = field[p];
        field[p] = c;
        return t;
    }

    public int discard(Card crd, Deck d) {
        if(crd != null) {
            d.add(crd, true);
            if (crd.value == Number.Jack) {
                return 1;
            }
            if (crd.value == Number.Queen) {
                return 2;
            }
            if (crd.value == Number.King) {
                return 3;
            }
        }
        return 0;
    }

    public void swap(int a){
        swap(a,6);
    }
    public void swap(int a,int b){
        Card t = field[b];
        field[b] = field[a];
        field[a] = t;
    }

    public boolean win(){
        if(field[0] == null||field[1]==null||field[2]==null){
            return false;
        }
        if(field[0].value.getVal()-1==field[1].value.getVal() && field[1].value.getVal()-1==field[2].value.getVal()){
            return true;
        }
        if(field[0].value.getVal()+1==field[1].value.getVal() && field[1].value.getVal()+1==field[2].value.getVal()){
            return true;
        }
        if(field[1].value==Number.King &&
                ( (field[0].value==Number.Queen && field[2].value==Number.Ace) ||
                        (field[2].value==Number.Queen && field[0].value==Number.Ace))){
            return true;
        }
        return field[1].value == Number.Ace &&
                ((field[0].value == Number.King && field[2].value == Number.Two) ||
                        (field[2].value == Number.King && field[0].value == Number.Two));
    }
    public String visible(){
        return String.format("%1$3s ", Card.shorted(field[0])) + String.format("%1$3s ",Card.shorted(field[1])) + String.format("%1$3s\n",Card.shorted(field[2]))+String.format("%1$14s",Card.shorted(field[6]));
    }
    @Override
    public String toString(){
        String str = visible();
        return str+String.format("\n%1$3s", Card.shorted(field[3]))+String.format(" %1$3s",Card.shorted(field[4]))+String.format(" %1$3s",Card.shorted(field[5]));
    }

}
