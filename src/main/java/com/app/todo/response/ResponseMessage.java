package com.app.todo.response;


import com.app.todo.domain.Board;
import com.app.todo.domain.Task;

import java.util.List;
import java.util.Map;

public class ResponseMessage {
    private String message;
    private String status;

    private Map<String, List<Task>> tasksByStatus;
    private List<Board> boardList;
    private List<Task> tasks;


    public ResponseMessage() {
    }

    public ResponseMessage(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public void setOkStatus() {
        setStatus("OK");
        setMessage("the request was successful");
    }

    public void setBadRequestStatus(String param) {
        setStatus("KO");
        setMessage("Param " + param + " has not value");
    }

    public void setError(String error) {
        setStatus("KO");
        setMessage("Error:  " + error);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, List<Task>> getTasksByStatus() {
        return tasksByStatus;
    }

    public void setTasksByStatus(Map<String, List<Task>> tasksByStatus) {
        this.tasksByStatus = tasksByStatus;
    }

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}