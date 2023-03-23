package top.hanjie.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class GoodsDto {
    public static class GetOne {
        @Data
        public static class Out {
            private Long id;
            private String name;
            private BigDecimal price;
            private String iconUrl;
        }
        @Data
        public static class In {
            @NotNull(message = "dto.goods.getOne.id.required")
            private Long id;
        }
    }
    public static class Save {
        @Data
        public static class In {
            private Long id;
            private String name;
            private BigDecimal price;
            private String iconUrl;
        }
    }
}
