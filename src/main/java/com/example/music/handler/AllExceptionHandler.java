package com.example.music.handler;

import com.example.music.constant.Status;
import com.example.music.model.ApiResponse;
import com.example.music.model.exception.JsonException;
import com.example.music.model.exception.PageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Late-en
 */

@ControllerAdvice
@Slf4j
public class AllExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse CommonException(Exception exception){
        log.error("【CommonException】:", exception);
        return ApiResponse.ofStatus(Status.UNKNOWN_ERROR, Status.UNKNOWN_ERROR.getMessage());
    }

    @ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public ApiResponse JsonException(JsonException exception){
        log.error("【JsonException】:{}", exception.getMessage());
        return ApiResponse.ofException(exception);
    }

    @ExceptionHandler(value = PageException.class)
    public ModelAndView PageException(PageException exception){
        log.error("【PageException】:{}", exception.getMessage());
        ModelAndView view = new ModelAndView();
        view.addObject("message", exception.getMessage());
        view.setViewName("error");
        return view;
    }
}
