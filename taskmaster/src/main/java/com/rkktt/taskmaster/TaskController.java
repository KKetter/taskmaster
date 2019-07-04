package com.rkktt.taskmaster;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class TaskController {
    //Johnny Winters provided status array implementation
    public String[] status = {"Available", "Assigned", "Accepted", "Finished"};

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    TaskInfoRepository taskInfoRepository;

    @GetMapping("/tasks")
    public List<TaskInfo> getTasks(){
        return (List<TaskInfo>) taskInfoRepository.findAll();
    }

    @PostMapping("/tasks")
    //A user should be able to make a POST request to /tasks with body parameters
    //for title and description to add a new task
    public @ResponseBody TaskInfo setTask(@RequestBody TaskInfo taskInfo){
        //start task with status of "Available"
        //add conditional logic if has assignee then status is set to Assigned, 0 if no assignee
        taskInfo.setStatus(status[0]);
        taskInfoRepository.save(taskInfo);
        return taskInfoRepository.findById(taskInfo.getId()).get();
    }
    //A user should be able to make a PUT request to /tasks/{id}/state to advance the status of that task.
    @PutMapping("/tasks/{id}/state")
    public void advanceStatus(@PathVariable String id){
        // get thing from db
        TaskInfo potato = taskInfoRepository.findById(id).get();
        //make the change - mtc
        //mtc-check current status
        String currentStatus = potato.getStatus();
        //mtc-compare to index of status array
        int statusIndex = Arrays.asList(status).indexOf(currentStatus);
        //mtc-increase index by 1 if less than or equal to 2
        if(statusIndex <= 2){
            potato.setStatus(status[statusIndex + 1]);
        }
        // save the change
        taskInfoRepository.save(potato);
    }
    @GetMapping("/users/{name}/tasks")
    public List<TaskInfo> getAssignee(@PathVariable String name) {
        System.out.println("LOOK HERERERERRERER DUDE" + name);
        return taskInfoRepository.findByAssignee(name);
    }
}

