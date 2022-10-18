package com.shree.awssqsspring.controller;

import com.shree.awssqsspring.model.MessageData;
import com.shree.awssqsspring.service.SqsSendReceiveMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/aws-demo/sqs")
public class AwsSqsController {

    @Autowired
    SqsSendReceiveMessages sqsSendReceiveMessages;

    @GetMapping(path = "/messages")
    public List<MessageData> listSqsMessages(){
        return sqsSendReceiveMessages.getMessages();

    }
}
