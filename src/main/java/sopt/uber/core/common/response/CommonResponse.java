package sopt.uber.core.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CommonResponse<T> {

    private int code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)  // null일 경우 응답에서 제외
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

    // Getters
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