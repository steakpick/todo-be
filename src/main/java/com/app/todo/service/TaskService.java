package com.app.todo.service;

import com.app.todo.domain.Task;
import com.app.todo.repository.TaskRepository;
import com.app.todo.response.ResponseMessage;
import com.app.todo.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<Task> getTasks(LocalDateTime dateTime) {
        List<Task> taskList = new ArrayList<>();
        try{
            taskList = taskRepository.getAllByCreationDate(dateTime);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return taskList;
    }

    public ResponseMessage addTask(String task) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            Task taskData = new ObjectMapper().readValue(task, Task.class);
            taskRepository.save(taskData);
            responseMessage.setOkStatus();
        }
        catch (Exception e){
            responseMessage.setError(e.getMessage());
        }

        return responseMessage;
    }
}
