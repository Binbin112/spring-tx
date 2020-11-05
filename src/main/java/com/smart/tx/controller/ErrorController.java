package com.smart.tx.controller;

import com.smart.tx.common.ResponseEntity;
import com.smart.tx.common.StatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常不能处理tomcat的错误
 */
@RestController
public class ErrorController {
    //    4xx
//  400   401  403  404
//   500 501 502
    @RequestMapping("/404")
    public ResponseEntity<Object> error404() {
        return ResponseEntity.error(StatusCode.ERROR);
    }

    @RequestMapping("/500")
    public ResponseEntity<Object> error500() {
        return ResponseEntity.error(StatusCode.SERVER_ERROR);
    }

}
