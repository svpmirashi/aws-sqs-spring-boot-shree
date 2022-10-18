package com.shree.awssqsspring.aws;

import com.shree.awssqsspring.model.MessageData;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;

import java.util.Map;
import java.util.Optional;

public class MessageConverter {
    public static MessageData convertSqsMessageToMessageData(Message message) {
        MessageData md = new MessageData();
        md.setBody(message.body());
        md.setId(message.messageId());

        Map<String, MessageAttributeValue> attributeValueMap = message.messageAttributes();

        if(attributeValueMap.size() == 0) md.setName("");
        else {
            MessageAttributeValue nameAttr = attributeValueMap.get("Name");

            if (Optional.of(nameAttr).isPresent())
                if (Optional.of(nameAttr.stringValue()).isPresent())
                    md.setName(nameAttr.stringValue());
        }
        return md;
    }
}
