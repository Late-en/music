package com.example.music.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @Description  
 * @Author Late-en  
 * @Date 2023-04-13 
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListSong implements Serializable {

	private static final long serialVersionUID =  1L;

	private Long id;

	private Long songId;

	private Long songListId;

}
