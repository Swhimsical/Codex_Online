import java.io.InputStream;

public class textGUI implements GUI{
    public textGUI() {
    }

    @Override
    public void swap(int a,int b) {
        String strA, strB;
        if(a>2){
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
            if(b>2){
                strB = "top ";
            } else {
                strB = "bottom ";
            }
            if(b%3==0){
                strB+=" left card";
            } else if(a%3==1){
                strB+=" middle card";
            } else {
                strB+=" right card";
            }
        }
        System.out.println("You swapped the "+strA+" card with the "+strB+".");
    }

    @Override
    public void draw(Card c) {

    }

    @Override
    public void win() {

    }

    @Override
    public void help() {
        System.out.println("Layout:\n 1 2 3\n       7\n 4 5 6" +
                "\nswap # or s # to swap that card with the choke." +
                "\ndraw or d to draw a card. Then enter a number to place it or 0 to discard it." +
                "\ncheck or c to check your face-down cards");
    }

    @Override
    public void prompt(int p) {
        System.out.println("\nMake your move player "+(p+1)+". Use h for help:\n");
    }

    @Override
    public InputStream source() {
        return System.in;
    }
}
