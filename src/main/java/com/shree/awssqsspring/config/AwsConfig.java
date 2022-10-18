package com.shree.awssqsspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
    @Value("${aws.sqs.region}")
    private String awsSqsRegion;


}
