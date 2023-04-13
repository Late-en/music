package com.example.music.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author Late-en  
 * @Date 2023-04-13 
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Singer implements Serializable {

	private static final long serialVersionUID =  1L;

	private Long id;

	private String name;

	private Integer sex;

	private String pic;

	private Date birth;

	private String location;

	private String introduction;

}
