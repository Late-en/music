package com.example.music.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author Late-en  
 * @Date 2023-04-18 
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {

	private static final long serialVersionUID =  1L;

	private Long id;

	private Long userId;

	private Long songId;

	private Long songListId;

	private String content;

	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	private Integer type;

	private Long up;
}
