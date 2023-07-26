package io.github.jaywong1024.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.OutputStream;
import java.util.Date;
import java.util.Objects;

/**
 * MinIO 工具类
 *
 * @author 黄汉杰
 */
@Slf4j
@Component
public class MinioUtils {

    @Resource
    private MinioClient minioClient;

    private final long FILE_MAX_SIZE = 1024 * 1024;

    /**
     * 创建桶
     *
     * @param bucketName 桶名称
     * @author 黄汉杰
     * @date 2022/6/30 0030 10:59
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        assert StringUtils.isNotBlank(bucketName);
        if (BooleanUtil.isFalse(minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build()))) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    public String getFilename(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        assert StringUtils.isNotBlank(originalFilename);
        return DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN)
                + System.currentTimeMillis()
                + originalFilename.substring(originalFilename.lastIndexOf("."));
    }

    @SneakyThrows
    public void compress(MultipartFile file, String filename) {
        log.info("before size: {}", file.getSize());
        if (file.getSize() > FILE_MAX_SIZE) {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            FileItem fileItem = fileItemFactory.createItem(filename, "text/plain", true, filename);
            OutputStream outputStream = fileItem.getOutputStream();
            Thumbnails.of(file.getInputStream()).scale(1f)
                    .outputFormat(Objects.requireNonNull(file.getOriginalFilename())
                            .substring(file.getOriginalFilename().lastIndexOf(".") + 1))
                    .outputQuality(0.25f).toOutputStream(outputStream);
            file = new CommonsMultipartFile(fileItem);
        }
        log.info("after size: {}", file.getSize());
    }

    @SneakyThrows
    public String upload(MultipartFile file, String bucketName) {
        // 1.判断文件是否为空
        if (Objects.isNull(file) || Objects.equals(file.getSize(), 0)) {
            return null;
        }
        // 2.判断桶是否存在，不存在则创建
        this.createBucket(bucketName);
        // 3.获取文件名
        String filename = this.getFilename(file);
        // 4.压缩
//        this.compress(file, filename);
        // 5.上传 MinIO
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName)
                .object(filename)
                .stream(file.getInputStream(), file.getSize(), -1L)
                .contentType(file.getContentType())
                .build());
        return "/" + bucketName + "/" + filename;
    }

}