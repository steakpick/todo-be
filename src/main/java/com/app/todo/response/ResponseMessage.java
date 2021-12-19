package com.app.todo.response;


import com.app.todo.domain.Task;

import java.util.List;

public class ResponseMessage {
    private String message;
    private String status;

    private List<Task> taskList;

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

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}