package io.github.jaywong1024.design.behavioral.observer;

import lombok.SneakyThrows;

public class Test {
    @SneakyThrows
    public static void main(String[] args) {
        LolUp write55 = new LolUp("write55", "全体起立！全体起立！");
        Audience jay = new Audience();
        // 添加观众（观察者）
        write55.addObserver(jay);
        // 开播
        write55.goOnline();
        // 光速下播
        Thread.sleep(60_000 * 2 + 10_000);
        write55.offline();
    }
}
