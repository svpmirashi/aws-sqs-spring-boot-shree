package com.shree.awssqsspring.service;

import com.shree.awssqsspring.aws.AwsClientFactory;
import com.shree.awssqsspring.aws.AwsServiceType;
import com.shree.awssqsspring.aws.MessageConverter;
import com.shree.awssqsspring.model.MessageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SqsSendReceiveMessages {

    private static final Logger logger = LoggerFactory.getLogger(SqsSendReceiveMessages.class);

    @Value("${aws.sqs.queueName}")
    private String queueName;

    @Value("${aws.sqs.maxNumberOfMessages}")
    private int maxNumberOfMessages;

    public List<MessageData> getMessages() {
        try {
            logger.info(String.format("Configured aws.sqs.queueName: '%s'; aws.sqs.maxNumberOfMessages: '%d'", queueName,maxNumberOfMessages));

            SqsClient sqsClient = (SqsClient) AwsClientFactory.getClient(AwsServiceType.SQS);

            GetQueueUrlRequest getQueueUrlRequest = GetQueueUrlRequest.builder()
                    .queueName(queueName)
                    .build();

            String queueURL = sqsClient.getQueueUrl(getQueueUrlRequest).queueUrl();

            // Receive messages
            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                    .queueUrl(queueURL)
                    .maxNumberOfMessages(maxNumberOfMessages)
                    .build();

            List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();

            messages.forEach(m -> logger.info("SQS Message body: " + m.body()));

            return messages.stream()
                    .map(m -> MessageConverter.convertSqsMessageToMessageData(m))
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            logger.info("SHREE# Exception while accessing AWS SQS queue: " + ex);
            //Optional.of(ex.getCause()).ifPresent(cause -> logger.debug("SHREE# " + cause.toString()));
        }

        return null;
    }
}
