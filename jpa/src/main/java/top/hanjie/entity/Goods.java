package top.hanjie.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 商品类
 */
@Data
@Entity // 表示这是一个数据对象类
// 对应数据库中的 goods 表
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "商品id")
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(value = "商品名称")
    private String name;

    @Column(name = "price")
    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @Column(name = "icon_url")
    @ApiModelProperty(value = "商品 icon url")
    private String iconUrl;

}
