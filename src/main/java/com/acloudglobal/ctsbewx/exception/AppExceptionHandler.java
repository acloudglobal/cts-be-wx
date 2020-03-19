package com.acloudglobal.ctsbewx.exception;


import com.acloudglobal.ctsbewx.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常统一处理器
 *
 * @author yupl@acloudchina.com
 * @date 2019-12-10 10:39 上午
 * @since V2.0.0
 */
@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorDTO(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler({AppException.class,Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO appException(Exception e) {
        return new ErrorDTO(e.getMessage());
    }

}
