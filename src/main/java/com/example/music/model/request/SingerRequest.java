package com.example.music.model.request;

import lombok.Data;

import java.util.Date;

/**
 * @author Late-en
 */
@Data
public class SingerRequest {
    private Long id;

    private String name;

    private Integer sex;

    private String pic;

    private Date birth;

    private String location;

    private String introduction;
}
