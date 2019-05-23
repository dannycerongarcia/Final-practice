package Observer;

import Observer.Observer;

import java.util.HashSet;
import java.util.Set;

public class Publisher {
    public int publisherValue = 0;
    Set<Observer> observerset = new HashSet<>();

    public void register(Observer observer)
    {
        observerset.add(observer);
    }

    public void setState(int publisherValue)
    {
        this.publisherValue = publisherValue;
        System.out.println("\r");
        for (Observer o: observerset)
        {
            o.render(publisherValue);
            System.out.print(" --- ");
        }
    }
}
