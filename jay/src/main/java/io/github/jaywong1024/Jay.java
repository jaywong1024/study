package io.github.jaywong1024;

public class Jay {

    private final static String[] SONGS = new String[]{
            "夜曲", "龙卷风", "最伟大的作品", "半岛铁盒", "回到过去",
            "以父之名", "一路向北", "最长的电影", "简单爱", "晴天",
            "七里香", "青花瓷", "千里之外", "安静", "听妈妈的话",
            "发如雪", "甜甜的", "稻香", "告白气球", "等你下课",
    };
    private final static int LEN = SONGS.length;

    public String randomPlay() {
        return SONGS[(int) (Math.random() * LEN)];
    }

    public static void main(String[] args) {
        Jay jay = new Jay();
        for (int i = 0; i < 10; i++) {
            System.out.println(jay.randomPlay());
        }
    }

}
