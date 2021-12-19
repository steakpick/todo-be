package com.app.todo.controller;

import com.app.todo.response.ResponseMessage;
import com.app.todo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MainController {
    final
    TaskService taskService;

    public MainController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task")
    public ResponseEntity<ResponseMessage> getTasks(
            @RequestParam(name = "datetime", required = false) String datetime,
            @RequestParam(name = "datetype", required = false) String datetype) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            responseMessage = taskService.getTasks(datetime, datetype);
        }
        catch (Exception e){
            responseMessage.setError(e.getMessage());
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/addTask")
    public ResponseEntity<ResponseMessage> addTasks(@RequestBody String task) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            responseMessage = taskService.addTask(task);
        }
        catch (Exception e){
            responseMessage.setError(e.getMessage());
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
