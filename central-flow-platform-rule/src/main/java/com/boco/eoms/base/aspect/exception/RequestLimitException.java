package com.boco.eoms.base.aspect.exception;

/**
 * 自定义异常类
 * Created by gang on 2018/3/16.
 */
public class RequestLimitException extends Exception {
    private static final long serialVersionUID = 1364225358754654702L;

    public RequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }

}
