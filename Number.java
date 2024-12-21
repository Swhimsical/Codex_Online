public enum Number {
    Ace(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    Jack(11),
    Queen(12),
    King(13),
    Joker(15);
    

    private final int val;
    private final String shorthand;
    Number(int val){
        this.val=val;
        String[] sh = new String[]{" ","A","2","3","4","5","6","7","8","9","10","J","Q","K","Jo"};
        shorthand = sh[val];
    }
    public int getVal() {
        return val;
    }

    public String getShorthand(){
        return shorthand;
    }

    public static Number label(int n){
        for (Number e: values()) {
            if(e.val==n){
                return e;
            }
        }
        return null;
    }

}
