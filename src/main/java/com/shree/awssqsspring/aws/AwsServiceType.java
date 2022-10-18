package com.shree.awssqsspring.aws;

public enum AwsServiceType {
    SQS("SQS"),
    SNS("SnS"),
    S3("S3");

    private String value;
    AwsServiceType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
