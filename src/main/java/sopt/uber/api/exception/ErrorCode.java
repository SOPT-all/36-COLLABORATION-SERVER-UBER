package sopt.uber.api.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다. 요청 형식을 확인해주세요."),
    SAME_LOCATION(HttpStatus.BAD_REQUEST, "출발지와 목적지는 같을 수 없습니다."),
    INVALID_LOCATION(HttpStatus.BAD_REQUEST, "출발지와 도착지 모두 입력해주세요."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "지원하지 않는 URL입니다."),
    NOT_FOUND_ID(HttpStatus.NOT_FOUND, "존재하지 않는 id값입니다"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "잘못된 HTTP method 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");



    private final HttpStatus httpStatus;
    private final String msg;

    ErrorCode(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.msg = msg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMsg() {
        return msg;
    }
}