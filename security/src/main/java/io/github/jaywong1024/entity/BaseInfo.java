package io.github.jaywong1024.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础信息
 *
 * @author 黄汉杰
 */
@Data
@Accessors(chain = true)
public class BaseInfo implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 状态（0正常；1删除）
     */
    private Integer status;
    /**
     * 创建人主键
     */
    private String createId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新人主键
     */
    private String updateId;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}