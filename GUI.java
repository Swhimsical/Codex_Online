import java.io.InputStream;

public interface GUI {
    public void swap(int a, int b);
    public void draw(Card c);
    public void win();
    public void help();

    public void prompt(int p);

    public InputStream source();
}
