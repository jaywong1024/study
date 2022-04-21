package top.hanjie.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.hanjie.bean.ResultBean;
import top.hanjie.exception.BusinessException;
import top.hanjie.utils.MsgUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * @author 黄汉杰
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResultBean<?> businessException(BusinessException e, HttpServletRequest req) {
        String msg = MsgUtils.getMsg(e.getMessage(), e.getArgs());
        return ResultBean.error(msg, e.getCause());
    }

}