import java.io.InputStream;

public class textGUI implements GUI{
    public textGUI() {
    }

    @Override
    public void swap(char act, int a,int b) {
        String strA, strB;
        if(a<3){
            strA = "top ";
        } else {
            strA = "bottom ";
        }
        if(a%3==0){
            strA+=" left";
        } else if(a%3==1){
            strA+=" middle";
        } else {
            strA+=" right";
        }

        if(b==6){
            strB = "choke";
        } else {
            if(b<3){
                strB = "top ";
            } else {
                strB = "bottom ";
            }
            if(b%3==0){
                strB+=" left card";
            } else if(b%3==1){
                strB+=" middle card";
            } else {
                strB+=" right card";
            }
        }
        if(act=='m') {
            System.out.println("You swapped the " + strA + " card with the " + strB + ".");
        } else {
            System.out.println("You swapped your opponent's " + strA + " card with the " + strB + ".");
        }
    }

    @Override
    public void draw(Card c) {
        System.out.println("you drew the "+c+" ("+c.shorthand()+")");
    }
    @Override
    public void turnstart(Player p, Player opp, int pint, Card grave){
        System.out.printf(opp.visible() + "\n\n     #%1$5s\n\n" + p + "%n",Card.shorted(grave));
    }
    @Override
    public void place(Card c, int i) {
        String strI = "choke";
        if(i!=6){
            if(i<3){
                strI = "top ";
            } else {
                strI = "bottom ";
            }
            if(i%3==0){
                strI+=" left card";
            } else if(i%3==1){
                strI+=" middle card";
            } else {
                strI+=" right card";
            }
        }
        System.out.println("you placed the "+c+"("+c.shorthand()+") as the "+strI);
    }

    @Override
    public void discard(Card c) {
        if(c == null){
            return;
        }
        System.out.println("you discarded the "+c+" ("+c.shorthand()+")");
    }
    @Override
    public void win() {

    }

    @Override
    public void help() {
        System.out.println("""
                Layout:
                 1 2 3
                       7
                 4 5 6
                swap # or s # to swap that card with the choke.
                draw or d to draw a card. Then enter a number to place it or 0 to discard it.
                check or c to check your face-down cards""");
    }

    @Override
    public void prompt(int p, char o) {
        if (o == 'T') {
            System.out.println("\nMake your move player " + (p + 1) + ". Use h for help:");
        } else if (o=='D'){
            System.out.println("Where would you like to place it? Use: # (0 to discard, 1-7 for each space)");
        } else if (o=='J'){
            System.out.println("What would you like to swap? Use: {m/o} # # (1-7 for each space)");
        } else if (o=='Q'){
            System.out.println("What would you like to flip? Use: {m/o} # (1-3 for each stack)");
        } else if (o=='K'){
            System.out.println("Your opponent has one action next round.");
        } else if (o=='N'){
            System.out.println("You do not have enough actions to win");
        } else if (o=='W'){
            System.out.println("Congratulations!\n Player "+(p+1)+" has won the game!");
        }
    }

    @Override
    public InputStream source() {
        return System.in;
    }
}
