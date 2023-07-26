package io.github.jaywong1024.exception;

import lombok.Getter;

/**
 * 业务异常
 * @author 黄汉杰
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误参数
     */
    private final String[] args;

    public BusinessException(String msg, String...args) {
        super(msg);
        this.args = args;
    }

}