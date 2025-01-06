import java.util.*;

public class GameRules {
    Player p1;
    Player p2;
    Deck deck;
    Deck grave;
    int Active;
    Scanner read;
    GUI gui;
    boolean[] kng;
    int[] recent;
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
        recent = new int[1];

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
        int act = actions+1;
        while (actions!=0){
            recent[0] = -1;
            if(act!=actions){
                gui.turnstart(p,opp,pint,grave.top());
                act=actions;
            }
            gui.prompt(Active,'T');
            text = read.nextLine();
            if(!text.isBlank()){
                line = text.split(" ");
                if(line[0].equals("h")||line[0].equals("help")){
                    gui.help();
                }
                if(line[0].equals("w")||line[0].equals("win")&& p.win()){
                    if(actions!=2){
                        gui.prompt(Active,'N');
                    } else {
                        gui.prompt(Active,'W');
                        return true;
                    }
                }

                if((line[0].equals("s")||line[0].equals("swap"))&&line.length==2&&line[1].matches("\\d")){
                    int l = Integer.parseInt(line[1])-1;
                    p.swap(l);
                    gui.swap('m',l,6);
                    actions--;
                    recent[0] = l;
                }

                if(line[0].equals("d")||line[0].equals("draw")){
                    drawCard(p, opp,null);
                    actions--;
                }
            }
            if(recent[0]>=0){
                RuleOfOne(p,opp);
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
            looping = !turn(plrs[Active],plrs[other],Active);
            if(Active==0){
                Active=1;
            } else {
                Active = 0;
            }
        }
    }
    public void drawCard(Player p, Player opp, boolean[] allowed){
        Card crd = deck.draw();
        gui.draw(crd);
        gui.prompt(Active,'D');
        String text = "x";
        int l=-2;
        while(l==-2) {
            while (!(text.matches("\\d") && text.charAt(0) < '8')) {
                text = read.nextLine();
            }
            l = Integer.parseInt(text) - 1;
            if(allowed!=null && !allowed[l]){
                l=-2;
                gui.prompt(Active,'d');
            }
        }
        recent[0] = -1;
        if(l>=0){
            gui.place(crd,l);
            crd = p.place(crd,l);
            if(l<3) {
                recent[0] = l;
            }
        }

        l=p.discard(crd,grave);
        gui.discard(crd);
        discard(l,p,opp);
    }
    public void discard(int A,Player p,Player opp){
        String text = "x";
        if(A==3){
            kng[0] = true;
            gui.prompt(Active,'K');
        } else if(A==2){
            gui.prompt(Active,'Q');
            while(!((text.matches("m\\d") || text.matches("o\\d"))&&text.charAt(1)<'4'&&text.charAt(1)!='0')){
                text = read.nextLine();
                text=text.replaceAll("\\s+","");
            }
            int t1 = text.charAt(1)-'1';
            if(text.charAt(0)=='m'){
                p.swap(t1,t1+3);
                gui.swap('m',t1,t1+3);
                recent[0]=t1;
            } else {
                opp.swap(t1,t1+3);
                gui.swap('o',t1,t1+3);
                recent[0]=t1+6;
            }

        } else if(A==1){
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
    }

    public boolean Ro1Loop(Player p, Player opp, int spot, int looped, boolean[] visited){ //FIXME: ADD A GUI PROMPT TO EACH FLIP
        System.out.println("Spot: "+spot);
        List<Card> tops = new ArrayList<>(p.faceUp());
        tops.addAll(opp.faceUp());
        if(looped>11){
            return true;
        }
        visited[spot]=true;
        visited[spot+6]=true;
        for(int i = 0; i < 3;i++){
            Card t = tops.get(i);
            if(i!= spot && t != null && tops.get(spot)!=null && tops.get(spot).value==t.value){
                p.swap(i,i+3);
                return Ro1Loop(p,opp,i,looped+1,visited);
            }
        }
        for(int i = 3; i < 6;i++){
            Card t = tops.get(i);
            if(i!= spot && t != null && tops.get(spot)!=null && tops.get(spot).value==t.value){
                opp.swap(i-3,i);
                return Ro1Loop(p,opp,i,looped+1,visited);
            }
        }
        return false;
    }
    public void RuleOfOne(Player p, Player opp){
        boolean[] visited = new boolean[12];
        if(Ro1Loop(p,opp,recent[0],0,visited)){
            gui.prompt(Active,'L');
            Card c1, c2;
            int i1 = 0, i2 = 0;
            while (i1 == i2) {
                c1 = deck.draw();
                c2 = deck.draw();
                i1 = c1.value.getVal();
                i2 = c2.value.getVal();
                gui.tie(Active, c1,c2);
            }
            if(i1>i2&&Active==0 || i2>i1&&Active==1){
                gui.prompt(Active,'1');
                drawCard(opp,p,Arrays.copyOfRange(visited,0,6));
            } else {
                gui.prompt(Active,'2');
                drawCard(p,opp, Arrays.copyOfRange(visited,7,12 ));
            }
        }
    }
}
