package io.github.jaywong1024.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.jaywong1024.rocksdb.KeyValueRepository;

/**
 * 测试类
 *
 * @author 黄汉杰
 */
@Slf4j
@RestController
@RequestMapping("/rocksDb")
public class TestController {

    private final KeyValueRepository<String, String> keyValueRepository;

    public TestController(KeyValueRepository<String, String> keyValueRepository) {
        this.keyValueRepository = keyValueRepository;
    }

    @PostMapping("/{key}")
    public ResponseEntity<String> save(@PathVariable("key") String key, @RequestBody String value) {
        log.info("TestController.save");
        this.keyValueRepository.save(key, value);
        return ResponseEntity.ok(value);
    }

    @PutMapping("/{key}")
    public ResponseEntity<String> merge(@PathVariable("key") String key, @RequestBody String value) {
        log.info("TestController.merge");
        this.keyValueRepository.merge(key, value);
        return ResponseEntity.ok(value);
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> find(@PathVariable("key") String key) {
        log.info("TestController.find");
        String result = this.keyValueRepository.find(key);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<String> delete(@PathVariable("key") String key) {
        log.info("TestController.delete");
        this.keyValueRepository.delete(key);
        return ResponseEntity.ok(key);
    }

}