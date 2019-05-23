package Observer;

public class Observer {
    private String text;

    public Observer(String text)
    {
        this.text = text;
    }

    public void render(int value)
    {
        System.out.print(text+" sees: "+value);
    }
}
