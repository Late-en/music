package com.example.music.dao;

import com.example.music.entity.RankList;

public interface RankListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank_list
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank_list
     *
     * @mbg.generated
     */
    int insert(RankList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank_list
     *
     * @mbg.generated
     */
    int insertSelective(RankList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank_list
     *
     * @mbg.generated
     */
    RankList selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank_list
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RankList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank_list
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RankList record);
}