package com.rkktt.taskmaster;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

    @Configuration
    @EnableDynamoDBRepositories (basePackages = "com.rkktt.taskmaster")
    public class DynamoDBConfig {
        //pasted from app.properties
//        amazon.dynamodb.endpoint=
//        amazon.aws.accesskey=${AWS_ACCESS_KEY}
//        amazon.aws.secretkey=${AWS_SECRET_KEY}
        @Value("${amazon.dynamodb.endpoint}")
        private String amazonDynamoDBEndpoint;

        @Value("${amazon.aws.accesskey}")
        private String amazonAWSAccessKey;

        @Value("${amazon.aws.secretkey}")
        private String amazonAWSSecretKey;

        @Bean
        public AmazonDynamoDB amazonDynamoDB() {
            AmazonDynamoDB amazonDynamoDB
                    = new AmazonDynamoDBClient(amazonAWSCredentials());
            //double check this region later
            amazonDynamoDB.setRegion(Region.getRegion(Regions.US_WEST_2));

            if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
                amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
            }

            return amazonDynamoDB;
        }

        @Bean
        public AWSCredentials amazonAWSCredentials() {
            return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
        }

        @Bean
        public DynamoDB dynamoDB(AmazonDynamoDB amazonDynamoDB){return new DynamoDB(amazonDynamoDB);}

    }

