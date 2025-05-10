package com.example.kafkalogconsumer.controller;

import com.example.kafkalogconsumer.model.ActionDTO;
import com.example.kafkalogconsumer.model.UserActionData;
import com.example.kafkalogconsumer.service.MyConsumer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data")
public class ConsumerController {

    private final MyConsumer myConsumer;

    public ConsumerController(MyConsumer myConsumer) {
        this.myConsumer = myConsumer;
    }

    @GetMapping
    public ResponseEntity<List<UserActionData>> getAllActions(){
          return ResponseEntity.ok().body(myConsumer.getAllActions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActionDTO> getUserActionById(@PathVariable Long id){
        return ResponseEntity.ok().body(myConsumer.getUserAction(id));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserActionData> getDataById(@PathVariable Long id){
//        return ResponseEntity.ok().body(myConsumer.getUserAction(id));
//    }

}
