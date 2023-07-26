package io.github.jaywong1024.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.github.jaywong1024.bean.ResultBean;
import io.github.jaywong1024.utils.MinioUtils;

import javax.annotation.Resource;

/**
 * 文件控制器
 *
 * @author 黄汉杰
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Resource(name = "minioUtils")
    private MinioUtils minioUtils;

    @PostMapping("upload")
    public ResultBean<String> upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam("bucketName") String bucketName) {
        return ResultBean.ok(this.minioUtils.upload(file, bucketName));
    }

}