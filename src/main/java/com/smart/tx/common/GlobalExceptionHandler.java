package com.smart.tx.common;

import com.smart.tx.exception.AccountException;
import com.smart.tx.exception.ServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 第一个在类上面使用 RestControllerAdvice ControllerAdvice
 * <p>
 * 全局异常处理
 * - 错误信息 返回给客服端  (通用的结果集)
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    //第二步 定义方法 必须是public 声明 返回值是统一的结果集
    // 方法参数接受异常信息
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handler(Exception exception) {
        if (exception instanceof ServiceException) {
            return ResponseEntity.error(((ServiceException) exception).getStatus(), ((ServiceException) exception).getMsg());
        } else {
            return ResponseEntity.error(StatusCode.ERROR);
        }
    }
    //  用户的错误新
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<Object> hander(AccountException exception) {
        return ResponseEntity.error(exception.getStatus(), exception.getMsg());
    }

}
