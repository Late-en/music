package com.example.music.config;

import lombok.Data;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Late-en
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "spark")
@EnableConfigurationProperties
@Data
public class SparkConfig {

    private String sparkHome = ".";

    /**
     * appName 参数是一个在集群 UI 上展示应用程序的名称
     */
    private String appName = "sparkPatrol";

    /**
     * master 是一个 Spark，Mesos 或 YARN 的 cluster URL，或者指定为在 local mode（本地模式）中运行的 “local” 字符串
     */
    private String master = "local";

    @Bean
    @ConditionalOnMissingBean(SparkConf.class)
    public SparkConf sparkConf() {
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
        return conf;
    }

    @Bean
    @ConditionalOnMissingBean(JavaSparkContext.class)
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }
}
