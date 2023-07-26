package io.github.jaywong1024.concurrent.lock;

public class MonitorEnterAndExit {

    private int i;

    public void test() {
        synchronized (this) {
            ++i;
        }
    }

}
