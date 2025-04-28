package com.example.kafkalogconsumer.repository;

import com.example.kafkalogconsumer.model.UserActionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionRepository extends JpaRepository<UserActionData, Long> {
}
