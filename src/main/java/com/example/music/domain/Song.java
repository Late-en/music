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
public class Song implements Serializable {

	private static final long serialVersionUID =  1L;

	private Long id;

	private Long singerId;

	private String name;

	private String introduction;

	/**
	 * 发行时间
	 */
	private Date createTime;

	private Date updateTime;

	private String pic;

	private String lyric;

	private String url;

}
