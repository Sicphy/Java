package http.protocol;

public class ThreadPool {
    private static int threadNumber = 20;

    public Thread getThread(Runnable runnable) {
        if(threadNumber != 0) {
            return new Thread(runnable);
        } else {
            return null;
        }
    }

    public void freeThread() {
        if(threadNumber != 20) {
            threadNumber++;
        }
    }
}