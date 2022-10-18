package com.shree.awssqsspring.aws;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

public class AwsClientFactory {
    public static SdkClient getClient(AwsServiceType serviceType) {
        switch (serviceType) {

            case SQS:
                return SqsClient.builder()
                        .region(Region.AP_SOUTH_1)
                        .credentialsProvider(ProfileCredentialsProvider.create())
                        .build();

        }
        return null;
    }

}


