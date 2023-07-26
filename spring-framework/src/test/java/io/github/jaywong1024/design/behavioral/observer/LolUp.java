package io.github.jaywong1024.design.behavioral.observer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@EqualsAndHashCode(callSuper = true)
public class LolUp extends Observable {
    private final String name;
    private final String slogan;
    // 工作时常（分钟）
    private AtomicInteger workTime = new AtomicInteger(-1);
    // 是否开播
    private boolean online = Boolean.FALSE;
    // 开播与下播方法
    public void goOnline() {
        this.online = Boolean.TRUE;
        new Thread(this::workTime).start();
    }
    public void offline() {
        this.online = Boolean.FALSE;
        super.setChanged();
        super.notifyObservers(slogan);
    }
    @SneakyThrows
    private void workTime() {
        while (online) {
            this.workTime.incrementAndGet();
            super.setChanged();
            super.notifyObservers(slogan);
            Thread.sleep(60_000);
        }
    }
}
