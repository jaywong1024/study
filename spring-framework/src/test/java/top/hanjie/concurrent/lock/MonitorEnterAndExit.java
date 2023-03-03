package top.hanjie.concurrent.lock;

public class MonitorEnterAndExit {

    private int i;

    public void test() {
        synchronized (this) {
            ++i;
        }
    }

}
