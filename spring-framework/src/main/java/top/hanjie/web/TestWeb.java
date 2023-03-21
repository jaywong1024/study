package top.hanjie.web;

import cn.hutool.core.io.file.FileWriter;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 *
 * @author 黄汉杰
 * <p>描述：测试类<p>
 */
@RestController
@RequestMapping("test")
public class TestWeb {

    static class Test {
        Test(String str) {
            System.out.println(str);
        }
    }

    @SneakyThrows
    @GetMapping("kun")
    public Integer kun(Integer num, Boolean sleep) {
        List<Test> testList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            testList.add(new Test("坤流才是最屌的！"));
            if (sleep) {
                Thread.sleep(1000);
            }
        }
        return testList.size();
    }

    @GetMapping("write")
    public void write(Integer num) {
        FileWriter writer = new FileWriter("/root/test.txt");
        for (int i = 0; i < num; i++) {
            writer.append("鸡你太美");
        }
    }

}