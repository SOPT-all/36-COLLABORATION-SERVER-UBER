package sopt.uber.core.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CommonResponse<T> {

    private int code;
    private String msg;
    private T data;

    public CommonResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
