package Threads;

import java.util.concurrent.atomic.AtomicInteger;

public class Debater implements Runnable{
    private char direction;
    private AtomicInteger counter;
    private Integer requiredValue;
    private static int debaterCount = 0;
    private final int id = debaterCount++;
    private MainThread mainThread;
    public static volatile boolean canceled = false;

    public Debater(char direction, AtomicInteger counter, int requiredValue, MainThread mainThread){
        this.direction = direction;
        this.counter = counter;
        this.requiredValue = requiredValue;
        this.mainThread = mainThread;
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

    private synchronized void argue() {
        switch(direction) {
            case '+': counter.incrementAndGet();
            break;
            case '-': counter.decrementAndGet();
            break;
        }

        System.out.println("Thread with id" + id);

        if(counter.get() == requiredValue) {
            mainThread.setWinnerId(id);
            canceled = true;
            notifyAll();
        }
    }

    public synchronized void run() {
        while(counter.get() != requiredValue ) {
            if(canceled)
                break;
            this.argue();
            Thread.yield();
        }
    }
}
