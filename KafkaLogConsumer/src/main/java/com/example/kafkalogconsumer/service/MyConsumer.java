package com.example.kafkalogconsumer.service;


import com.example.kafkalogconsumer.model.ActionDTO;
import com.example.kafkalogconsumer.model.UserActionData;
import com.example.kafkalogconsumer.repository.UserActionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserActionRepository userActionRepository;
    private final Logger log = LoggerFactory.getLogger(MyConsumer.class);

    @Autowired
    public MyConsumer(UserActionRepository userActionRepository) {
        this.userActionRepository = userActionRepository;
    }

    @KafkaListener(topics = "web_logs", groupId = "webLogsGroup", containerFactory = "weblogsKafkaListenerContainerFactory")
    public void listen(String message) {

        try {
            log.debug("Take action : {}", message);

            UserActionData userAction = objectMapper.readValue(message, UserActionData.class);
            userActionRepository.save(userAction);

            log.info("Action save in DB : {}", userAction);

        } catch (JsonProcessingException e) {
            log.error("Failed to parse order from JSON: {}", message, e);
            throw new RuntimeException("Deserialization error", e);
        }

    }

    @Cacheable(value = "users_action", key = "'all'")
    public List<UserActionData> getAllActions() {
        return userActionRepository.findAll();
    }

    @Cacheable(value = "user_actions", key = "#id")
    public ActionDTO getUserAction(Long id) {
        UserActionData userAction = userActionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find product with id " + id));

        return new ActionDTO(userAction.getId(), userAction.getUserName(), userAction.getAction());
    }

//    @Cacheable(value = "user_actions", key = "#id")
//    public UserActionData getUserAction(Long id) {
//        UserActionData userAction = userActionRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Cannot find product with id " + id));
//
//        return userAction;
//    }
}
