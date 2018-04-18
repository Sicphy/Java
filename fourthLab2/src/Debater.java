import java.util.concurrent.atomic.AtomicInteger;

public class Debater implements Runnable{
    private char direction ;
    private AtomicInteger counter;
    private int requiredValue;
    private static int debaterCount = 0;
    private final int id = debaterCount++;

    public Debater(char direction, AtomicInteger counter, int requiredValue){
        this.direction = direction;
        this.counter = counter;
        this.requiredValue = requiredValue;
    }

    public int getId() {
        return this.id;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public char getDirection() {
        return this.direction;
    }

    public void argue() {
        switch(direction) {
            case '+': counter.incrementAndGet();
            break;
            case '-': counter.decrementAndGet();
            break;
        }

        if(counter.get() == requiredValue) {

        }
    }

    public void run() {
        while(counter.get() != requiredValue ) {
            this.argue();
            Thread.yield();
        }
    }
}
