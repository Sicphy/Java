package test.main;

import Threads.*;

import java.util.concurrent.TimeUnit;

public class test {
    public static void main(String[] args) {
        MainThread mainThread = new MainThread();
        mainThread.setRequiredValue(5);
        mainThread.createThread('+');
        mainThread.createThread('+');
        mainThread.createThread('+');
        mainThread.createThread('+');
        mainThread.createThread('+');
        mainThread.createThread('-');
        mainThread.createThread('-');
        mainThread.createThread('-');
        mainThread.startRace();

        System.out.println(mainThread.getWinnerId());
    }
}
