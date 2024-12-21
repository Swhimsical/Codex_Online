import java.util.Random;
import java.util.Scanner;

public class GameRules {
    Player p1;
    Player p2;
    Deck deck;
    int Active;
    Scanner read;
    public static Random r = new Random();
    public GameRules(Readable IS){
        this(r.nextBoolean(),IS);
    }

    public GameRules(boolean active,Readable IS){
        Random r = new Random();
        deck = new Deck();
        p1 = new Player(deck.drawX(3));
        p2 = new Player(deck.drawX(3));
        Active = active?1:0;
        read = new Scanner(IS);
    }
    public static boolean turn(Player p){
        int actions = 2;
        while (actions!=0){

        }
        return false;
    }
    public void gameloop(Readable IS){
        boolean looping = true;
        Player[] plrs = new Player[]{p1,p2};
        while (looping){
            looping = !turn(plrs[Active]);
        }
    }
}
