package Observer;

public class MainTwo {

    public static void main(String args[])throws InterruptedException
    {
        Publisher publisher = new Publisher();
        Observer observer1 = new Observer("observer 1");
        Observer observer2 = new Observer("observer 2");
        publisher.register(observer1);
        publisher.register(observer2);
        int observer_count=0;
        while (true)
        {
            observer_count++;
            publisher.setState(observer_count);
            Thread.sleep(1000);
        }
    }
}
