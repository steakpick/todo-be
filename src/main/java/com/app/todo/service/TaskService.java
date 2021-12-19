package com.app.todo.service;

import com.app.todo.domain.Task;
import com.app.todo.repository.TaskRepository;
import com.app.todo.response.ResponseMessage;
import com.app.todo.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public ResponseMessage getTasks(String datetimeStr, String dateType) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            LocalDateTime dateTime = Util.getLocalDateTimeFromString(datetimeStr, dateType);
            List<Task> taskList = taskRepository.getAllByCreationDate(dateTime);
            responseMessage.setOkStatus();
            responseMessage.setTaskList(taskList);
        }
        catch (Exception e){
            responseMessage.setError(e.getMessage());
        }

        return responseMessage;
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
