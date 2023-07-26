package io.github.jaywong1024.design.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

public class Audience implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof LolUp) {
            // 观察主播情况
            LolUp lolUp = (LolUp) o;
            System.out.println("观察结果，主播 " + lolUp.getName() + " 是否开播：" + lolUp.isOnline());
            System.out.println("主播开播时长（分钟）：" + lolUp.getWorkTime());
            System.out.println(arg);
        }
    }
}
