package com.example.music.constant;

import lombok.Getter;

/**
 * @author Late-en
 */

@Getter
public enum Status {
    OK(200,"success", true, "success"),
    WARNING(200, "warning", false, "warning"),
    UNKNOWN_ERROR(500,"服务器未知错误", false, "error");

    private Integer code;
    private String message;
    private boolean success;
    private String type;

    Status(Integer code, String message, boolean success, String type){
        this.code = code;
        this.message = message;
        this.success = success;
        this.type = type;
    }
}
