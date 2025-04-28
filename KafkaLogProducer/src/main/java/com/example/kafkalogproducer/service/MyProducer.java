package com.example.kafkalogproducer.service;

import com.example.kafkalogproducer.model.UserActionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class MyProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(MyProducer.class);

    @Autowired
    public MyProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void send(UserActionDTO userLogs)  {

        log.info("Take action : {}", userLogs);

         String key = userLogs.getUserName();

         try {
             String json = objectMapper.writeValueAsString(userLogs);
             kafkaTemplate.send("web_logs", key, json);
         } catch (JsonProcessingException e) {
             log.error("Failed to serialize order: {}", e.getMessage());
             throw new RuntimeException("Serialization error", e);
         }
    }

}




