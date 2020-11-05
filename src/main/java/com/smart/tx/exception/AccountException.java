package com.smart.tx.exception;

import com.smart.tx.common.StatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@EqualsAndHashCode(callSuper = true)
@Data
public class AccountException extends BaseException {
    private int status;
    private String msg;

    public AccountException() {
    }

    public AccountException(StatusCode statusCode) {
        this.status = statusCode.getStatus();
        this.msg = statusCode.getMsg();
    }
}
