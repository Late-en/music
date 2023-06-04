package com.example.music.service;

import com.example.music.MusicApplicationTests;
import com.example.music.recommender.StatisticsRecommender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Late-en
 */
@Slf4j
public class RecommenderTest extends MusicApplicationTests {
    @Autowired
    private StatisticsRecommender statisticsRecommender;

    @Test
    public void test1(){
        statisticsRecommender.run();
    }
}
