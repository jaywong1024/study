package io.github.jaywong1024.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "brand", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Apple.class),
        @JsonSubTypes.Type(value = Xiaomi.class)
})
public class Phone {
    /**
     * 品牌
     */
    @NotBlank(message = "phone.brand.required")
    private String brand;
}
