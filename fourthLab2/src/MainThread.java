import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class MainThread {
    private AtomicInteger counter = new AtomicInteger(0);
    private int requiredValue;
    private LinkedList<Thread> threads = new LinkedList<Thread>();

    public void setRequiredValue(int value) {
        this.requiredValue = value;
    }

    public int getRequiredValue() {
        return this.requiredValue;
    }

    public void createThread(char direction) {
        threads.add(new Thread(new Debater(direction, counter, requiredValue)));
    }

    public void destroyThread() {

    }
}
