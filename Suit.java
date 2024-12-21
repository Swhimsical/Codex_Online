public enum Suit {
    Spade(1),
    Club(2),
    Heart(3),
    Diamond(4);

    private final int val;
    private final char shorthand;
    Suit(int val){
        char[] sh = new char[]{' ','♠','♣','♥','♦'};
        this.val=val;
        shorthand = sh[val];
    }

    public int getVal() {
        return val;
    }

    public char getShorthand(){
        return shorthand;
    }

    public static Suit label(int n){
        for (Suit e: values()) {
            if(e.val==n){
                return e;
            }
        }
        return null;
    }
}
