package com.smart.tx.exception;


import com.smart.tx.common.StatusCode;
import lombok.Data;

/**
 *
 */
@Data
public class ServiceException extends BaseException {

    public ServiceException(StatusCode statusCode) {
        super(statusCode);
    }
}
