package Threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Debater implements Runnable{
    private char direction;
    private AtomicInteger counter;
    private Integer requiredValue;
    private static int debaterCount = 0;
    private final int id = debaterCount++;
    private MainThread mainThread;
    private static volatile boolean canceled = false;
    private ReentrantLock lock = new ReentrantLock();

    Debater(char direction, AtomicInteger counter, Integer requiredValue, MainThread mainThread){
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

    private void argue() {
        lock.lock();
        try {
            switch (direction) {
                case '+':
                    counter.incrementAndGet();
                    break;
                case '-':
                    counter.decrementAndGet();
                    break;
            }

           // System.out.println("Thread with id " + id + " count " + counter);

            if (counter.get() == requiredValue && !canceled) {
                System.out.println("Winner thread with id " + id);
                canceled = true;
                try {
                    mainThread.queue.put(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void run() {
        while(counter.get() != requiredValue ) {
            if(canceled)
                break;
            this.argue();
            Thread.yield();
        }
    }
}
