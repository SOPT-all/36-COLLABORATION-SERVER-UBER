package sopt.uber.core.common.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sopt.uber.core.common.response.CommonResponse;

public class ResponseUtil {

    // 성공 응답
    public static <T> ResponseEntity<CommonResponse<T>> success(T data) {
            return new ResponseEntity<>(new CommonResponse<>(200, "요청이 성공적으로 처리되었습니다.", data), HttpStatus.OK);
    }

    // 실패 응답
    public static <T> ResponseEntity<CommonResponse<T>> fail(HttpStatus status, String message) {
        return new ResponseEntity<>(new CommonResponse<>(status.value(), message), status);
    }
}
