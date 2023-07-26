package io.github.jaywong1024.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName(value = "apple")
public class Apple extends Phone {
    private String island;
}
