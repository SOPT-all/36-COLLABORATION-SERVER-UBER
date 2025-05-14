package sopt.uber.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import sopt.uber.core.common.response.CommonResponse;
import sopt.uber.core.common.util.ResponseUtil;

@RestControllerAdvice
class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResponse<Void>> handleBusinessException(BusinessException ex) {
        ErrorCode errorCode = ex.getFailCode();
        logger.error("BusinessException: {}", errorCode.getMsg(), ex);
        return ResponseUtil.fail(errorCode.getHttpStatus(), errorCode.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldError() != null
                ? ex.getBindingResult().getFieldError().getDefaultMessage()
                : ErrorCode.BAD_REQUEST.getMsg();
        logger.error("ValidationException: {}", errorMsg, ex);
        return ResponseUtil.fail(HttpStatus.BAD_REQUEST, errorMsg);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CommonResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error("HttpMessageNotReadableException: {}", ex.getMessage(), ex);
        return ResponseUtil.fail(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMsg());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<CommonResponse<Void>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        logger.error("NoHandlerFoundException: {}", ex.getMessage(), ex);
        return ResponseUtil.fail(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND.getMsg());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CommonResponse<Void>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        logger.error("HttpRequestMethodNotSupportedException: {}", ex.getMessage(), ex);
        return ResponseUtil.fail(HttpStatus.METHOD_NOT_ALLOWED, ErrorCode.METHOD_NOT_ALLOWED.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleUnexpectedException(Exception ex) {
        logger.error("UnexpectedException occurred: {}", ex.getMessage(), ex);
        return ResponseUtil.fail(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getMsg());
    }
}