package top.hanjie.rocksdb;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.rocksdb.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Rocks DB 工具类
 *
 * @author 黄汉杰
 */
@Slf4j
@Component
public class KeyValueRepositoryImpl implements KeyValueRepository<String, String> {

    private final static String PATCH = "/Users/huanghanjie/Project/Java/tmp/rocks-db";
    private final static String NAME = "rocks-db";
    protected RocksDB rocksDb;

    @PostConstruct
    void initialize() {
        RocksDB.loadLibrary();
        final Options options = new Options();
        options.setCreateIfMissing(true)
                // merge 操作
                .setMergeOperator(new StringAppendOperator());
        File dbDir = new File(PATCH, NAME);
        try {
            Files.createDirectories(dbDir.getParentFile().toPath());
            Files.createDirectories(dbDir.getAbsoluteFile().toPath());
            this.rocksDb = RocksDB.open(options, dbDir.getAbsolutePath());
        } catch (IOException | RocksDBException ex) {
            log.error("Error initializing RocksDB, check configurations and permissions, exception: {}, message: {}, stackTrace: {}",
                    ex.getCause(), ex.getMessage(), ex.getStackTrace());
        }
        log.info("RocksDB initialized and ready to use");
    }

    @Override
    public void save(String key, String value) {
        log.info("save");
        try {
            this.rocksDb.put(key.getBytes(), value.getBytes());
        } catch (RocksDBException e) {
            log.error("Error saving entry in RocksDB, cause: {}, message: {}", e.getCause(), e.getMessage());
        }
    }

    @Override
    public void merge(String key, String value) {
        log.info("merge");
        try {
            this.rocksDb.merge(key.getBytes(), value.getBytes());
        } catch (RocksDBException e) {
            log.error("Error merging entry in RocksDB, cause: {}, message: {}", e.getCause(), e.getMessage());
        }
    }

    @Override
    public String find(String key) {
        log.info("find");
        List<String> result = new ArrayList<>();
        RocksIterator iterator = this.rocksDb.newIterator();
        for (iterator.seek(key.getBytes()); iterator.isValid(); iterator.next()) {
            result.addAll(Arrays.asList(new String(iterator.value()).split(",")));
        }
        if (CollUtil.isEmpty(result)) {
            return null;
        }
        return JSONObject.toJSONString(result);
    }

    @Override
    public void delete(String key) {
        log.info("delete");
        try {
            this.rocksDb.delete(key.getBytes());
        } catch (RocksDBException e) {
            log.error("Error deleting entry in RocksDB, cause: {}, message: {}", e.getCause(), e.getMessage());
        }
    }

}