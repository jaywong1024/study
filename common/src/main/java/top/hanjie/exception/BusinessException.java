package top.hanjie.exception;

import lombok.Getter;

/**
 * 业务异常
 * @author 黄汉杰
 */
@Getter
public class BusinessException extends RuntimeException {

    private final String[] args;

    public BusinessException(String msg, String...args) {
        super(msg);
        this.args = args;
    }

}