package top.hanjie.concurrent.concurrent;

public class MonitorEnterAndExit {

    private int i;

    public void test() {
        synchronized (this) {
            ++i;
        }
    }

}
