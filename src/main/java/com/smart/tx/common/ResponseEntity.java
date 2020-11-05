package com.smart.tx.common;
import lombok.Data;

@Data
public class ResponseEntity<T> {
    private int status;
    private String msg;
    private T data;

    public static <T> ResponseEntity<T> error(StatusCode statusCode) {
        ResponseEntity<T> entity = new ResponseEntity<>();
        entity.setStatus(statusCode.getStatus());
        entity.setMsg(statusCode.getMsg());
        return entity;
    }

    public static <T> ResponseEntity<T> error(int status, String msg) {
        ResponseEntity<T> entity = new ResponseEntity<>();
        entity.setStatus(status);
        entity.setMsg(msg);
        return entity;
    }

}
