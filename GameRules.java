import java.util.Random;
import java.util.Scanner;

public class GameRules {
    Player p1;
    Player p2;
    Deck deck;
    int Active;
    Scanner read;
    GUI gui;
    boolean[] kng;
    public static Random r = new Random();
    public GameRules(GUI gui){
        this(r.nextBoolean(),gui);
    }

    public GameRules(boolean active,GUI gui){
        Random r = new Random();
        deck = new Deck();
        p1 = new Player(deck.drawX(3));
        p2 = new Player(deck.drawX(3));
        Active = active?1:0;
        kng = new boolean[]{true};

        this.gui = gui;
        read = new Scanner(gui.source());
    }
    public boolean turn(Player p,int pint){
        int actions = 2;
        if(kng[0]){
            actions = 1;
            kng[0]=false;
        }
        String text;
        String[] line;
        while (actions!=0){
            gui.prompt(pint);
            text = read.nextLine();
            if(!text.isBlank()){
                line = text.split(" ");
                if(line[0].equals("h")||line[0].equals("help")){
                    gui.help();
                    actions --; //FIXME: REMOVE
                }
            }



        }
        return false;
    }

    public void gameloop(){
        boolean looping = true;
        Player[] plrs = new Player[]{p1,p2};
        gui.help();
        while (looping){
            looping = !turn(plrs[Active],Active);
            if(Active==0){
                Active=1;
            } else {
                Active = 0;
            }
        }
    }
}
