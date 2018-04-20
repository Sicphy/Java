package Threads;

import java.util.LinkedList;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainThread {
    private AtomicInteger counter;
    private Integer requiredValue;
    private LinkedList<Thread> threads;
    private int winnerId;
    final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();

    public MainThread() {
        this.threads = new LinkedList<Thread>();
        //this.counter = new AtomicInteger(0);
        this.counter = new AtomicInteger(0);
        this.requiredValue = null;
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

    public int getWinnerId() {
        try {
           winnerId = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
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

}
