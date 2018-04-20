package test.main;

import Threads.*;

import java.util.concurrent.TimeUnit;

public class test {
    public static void main(String[] args) {
        MainThread mainThread = new MainThread();
        mainThread.setRequiredValue(45);
        mainThread.createThread('+');
        mainThread.createThread('+');
        mainThread.createThread('+');
        mainThread.createThread('-');
        mainThread.startRace();
        /*try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }*/

        System.out.println(mainThread.getWinnerId());
        mainThread.startRace();
        mainThread = new MainThread();
        System.out.println(mainThread.getWinnerId());
    }
}
