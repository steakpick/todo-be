package com.app.todo.controller;

import com.app.todo.response.ResponseMessage;
import com.app.todo.service.BoardService;
import com.app.todo.service.MainService;
import com.app.todo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin // hate u son of the bitch
@RequestMapping("/")
public class MainController {
    final
    TaskService taskService;

    final
    MainService mainService;

    final
    BoardService boardService;

    public MainController(TaskService taskService, MainService mainService, BoardService boardService) {
        this.taskService = taskService;
        this.mainService = mainService;
        this.boardService = boardService;
    }

    @GetMapping("/task")
    public ResponseEntity<ResponseMessage> getTasks(
            @RequestParam(name = "datetime", required = false) String datetime,
            @RequestParam(name = "datetype", required = false) String datetype) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            responseMessage = mainService.getSortedTasks(datetime, datetype);
        }
        catch (Exception e){
            responseMessage.setError(e.getMessage());
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/justtasks")
    public ResponseEntity<ResponseMessage> getJustTasks(
            @RequestParam(name = "datetime", required = false) String datetime,
            @RequestParam(name = "datetype", required = false) String datetype) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            responseMessage = mainService.getSimpleTasks(datetime, datetype);
        }
        catch (Exception e){
            responseMessage.setError(e.getMessage());
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
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

    @PostMapping("/addBoard")
    public ResponseEntity<ResponseMessage> addBoard(@RequestBody String board) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            responseMessage = boardService.addBoard(board);
        }
        catch (Exception e){
            responseMessage.setError(e.getMessage());
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
