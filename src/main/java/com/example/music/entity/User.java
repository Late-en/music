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
public class User implements Serializable {

	private static final long serialVersionUID =  1L;

	private Long id;

	private String username;

	private String password;

	private Integer sex;

	private String phoneNum;

	private String email;

	private Date birth;

	private String introduction;

	private String location;

	private String avatar;

	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}
