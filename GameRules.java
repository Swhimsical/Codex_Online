import java.util.Random;
import java.util.Scanner;

public class GameRules {
    Player p1;
    Player p2;
    Deck deck;
    Deck grave;
    int Active;
    Scanner read;
    GUI gui;
    boolean[] kng;
    public static Random r = new Random();
    public GameRules(GUI gui){
        this(r.nextBoolean(),gui);
    }

    public GameRules(boolean active,GUI gui){
        deck = new Deck(true);
        grave = new Deck(false);
        p1 = new Player(deck.drawX(3));
        p2 = new Player(deck.drawX(3));
        Active = active?1:0;
        kng = new boolean[]{true};

        this.gui = gui;
        read = new Scanner(gui.source());
    }
    public boolean turn(Player p, Player opp, int pint){
        int actions = 2;
        if(kng[0]){
            actions = 1;
            kng[0]=false;
        }
        String text;
        String[] line;
        gui.turnstart(p,opp,pint,grave.top());
        while (actions!=0){
            gui.prompt(Active,'T');
            text = read.nextLine();
            if(!text.isBlank()){
                line = text.split(" ");
                if(line[0].equals("h")||line[0].equals("help")){
                    gui.help();
                }

                if((line[0].equals("s")||line[0].equals("swap"))&&line.length==2&&line[1].matches("\\d")){
                    int l = Integer.parseInt(line[1])-1;
                    p.swap(l);
                    gui.swap('m',l,6);
                    actions--;
                }

                if(line[0].equals("d")||line[0].equals("draw")){
                    Card crd = deck.draw();
                    gui.draw(crd);
                    gui.prompt(Active,'D');
                    while(!text.matches("\\d")||text.charAt(0)>'7'){
                        text = read.nextLine();
                    }
                    int l = Integer.parseInt(text)-1;
                    if(l>=0){
                        gui.place(crd,l);
                        crd = p.place(crd,l);
                        l=p.discard(crd,grave);
                        gui.discard(crd);
                        text = "x";
                        if(l==3){
                            kng[0] = true;
                            gui.prompt(Active,'K');
                        } else if(l==2){
                            gui.prompt(Active,'Q');
                            while(!((text.matches("m\\d") || text.matches("o\\d"))&&text.charAt(1)<'4'&&text.charAt(1)!='0')){
                                text = read.nextLine();
                                text=text.replaceAll("\\s+","");
                            }
                            int t1 = text.charAt(1)-'1';
                            if(text.charAt(0)=='m'){
                                p.swap(t1,t1+3);
                                gui.swap('m',t1,t1-3);
                            } else {
                                opp.swap(t1,t1-3);
                                gui.swap('o',t1,t1-3);
                            }

                        } else if(l==1){
                            gui.prompt(Active,'J');
                            while(!((text.matches("m\\d\\d") || text.matches("o\\d\\d"))&&text.charAt(1)<'8'&&text.charAt(2)<'8'&&text.charAt(1)!='0'&&text.charAt(2)!='0')){
                                text = read.nextLine();
                                text = text.replaceAll("\\s+","");
                            }
                            int t1 = text.charAt(1)-'1';
                            int t2 = text.charAt(2)-'1';
                            if(text.charAt(0)=='m'){
                                p.swap(t1,t2);
                                gui.swap('m',t1,t2);
                            } else {
                                opp.swap(t1,t2);
                                gui.swap('o',t1,t2);
                            }
                        }
                    } else {
                        p.discard(crd,grave);
                        gui.discard(crd);
                    }

                    actions--;
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
            int other = (Active==0)?1:0;
            looping = !turn(plrs[Active],plrs[other],Active); //FIXME: IMPLEMENT A DISCARDED CARD FUNCTION
            if(Active==0){
                Active=1;
            } else {
                Active = 0;
            }
        }
    }
}
