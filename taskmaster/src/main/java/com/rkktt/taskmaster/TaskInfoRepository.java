package com.rkktt.taskmaster;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface TaskInfoRepository extends CrudRepository<TaskInfo, String> {
    Optional<TaskInfo> findById(String id);
}

