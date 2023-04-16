package com.example.music.entity;

public class RankList {
    private Long id;

    private Long songlistid;

    private Long userid;

    private Integer score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSonglistid() {
        return songlistid;
    }

    public void setSonglistid(Long songlistid) {
        this.songlistid = songlistid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}