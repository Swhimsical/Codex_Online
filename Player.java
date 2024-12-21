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

    public void swap(int a){
        swap(a,6);
    }
    public void swap(int a,int b){
        Card t = field[6];
        field[6] = field[a];
        field[a] = t;
    }

    public boolean win(){
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
        if(field[1].value==Number.Ace &&
                ( (field[0].value==Number.King && field[2].value==Number.Two) ||
                        (field[2].value==Number.King && field[0].value==Number.Two))){
            return true;
        }
        return false;
    }


}
