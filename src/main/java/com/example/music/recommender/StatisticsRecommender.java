package com.example.music.recommender;

import org.apache.spark.sql.*;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class StatisticsRecommender{
    public void run() {
        SparkSession spark = SparkSession.builder().appName("music application").getOrCreate();

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "875421963adc");
        connectionProperties.put("driver", "com.mysql.cj.jdbc.Driver");

        String jdbcUrl = "jdbc:mysql://localhost:3306/music";

        // 读取MySQL中的评分数据集
        spark.read().jdbc(jdbcUrl, "song_ratings", connectionProperties)
                .createOrReplaceTempView("song_ratings");
        spark.sql("SELECT song_id, COUNT(*) AS count FROM song_ratings GROUP BY song_id ORDER BY count DESC")
                .write().mode("overwrite")
                .jdbc(jdbcUrl, "song_ratings_number", connectionProperties);
        spark.stop();
    }
}
