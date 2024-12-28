import java.io.InputStream;

public interface GUI {
    void swap(char act, int a, int b);
     void draw(Card c);


    void turnstart(Player p, Player opp, int pint, Card grave);

    void place(Card c, int i);
    void discard(Card c);
     void win();
     void help();

     void prompt(int p,char O);

     InputStream source();
}
