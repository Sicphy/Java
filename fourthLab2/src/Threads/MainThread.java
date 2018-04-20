package Threads;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainThread {
    private AtomicInteger counter = new AtomicInteger(0);
    private Integer requiredValue = null;
    private LinkedList<Thread> threads = new LinkedList<Thread>();
    private int winnerId;

    public MainThread() {

    }

    public boolean isEmpty() {
        return threads.isEmpty();
    }

    public void setRequiredValue(int value) {
        this.requiredValue = value;
    }

    public Integer getRequiredValue() {
        return this.requiredValue;
    }

    public void setWinnerId(int id) {
        this.winnerId = id;
    }

    public synchronized int getWinnerId() {
        /*try {
            while(!Debater.canceled)
                //wait();
        } catch (InterruptedException e) {
            System.out.println("wait exception");
        }*/

        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }

        return this.winnerId;

    }

    public void createThread(char direction) {
        threads.add(new Thread(new Debater(direction, counter, requiredValue,this)));
    }

    public void startRace() {
        for(Thread t : threads) {
            t.start();
        }
    }

    public void destroyThread() {
        for(Thread t : threads) {
        }
    }
}
