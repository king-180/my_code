package com.wx.demo.exception;

import com.wx.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集中处理所有异常
 *
 * @author wangxing
 * @date 2021/4/16 16:11
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.wx.demo.controller")
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVO<Map<String, String>> handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题: {}, 异常类型：{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String, String> errorMap = new HashMap<>(16);
        fieldErrors.forEach((fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage())));
        return new ResultVO<>(400, "数据校验出现问题", errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public ResultVO<Map<String, String>> handleException(Throwable e) {
        log.error("出现问题: {}, 异常类型：{}", e.getMessage(), e.getClass());
        return new ResultVO<>(400, e.getMessage());
    }

}
