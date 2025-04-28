package com.example.kafkalogproducer.controller;

import com.example.kafkalogproducer.model.UserActionDTO;
import com.example.kafkalogproducer.service.MyProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ProducerController {

    private final MyProducer myProducer;

    @Autowired
    public ProducerController(MyProducer myProducer) {
        this.myProducer = myProducer;
    }


    @PostMapping("/action")
    public ResponseEntity<String> webLogs(@RequestBody UserActionDTO userLogs) throws JsonProcessingException {

        myProducer.send(userLogs);

        return ResponseEntity.ok("Message sent to Kafka");

    }

}
