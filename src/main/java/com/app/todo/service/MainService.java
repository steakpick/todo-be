package com.app.todo.service;

import com.app.todo.domain.Board;
import com.app.todo.domain.Task;
import com.app.todo.response.ResponseMessage;
import com.app.todo.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainService {
    final
    BoardService boardService;

    final
    TaskService taskService;

    public MainService(BoardService boardService, TaskService taskService) {
        this.boardService = boardService;
        this.taskService = taskService;
    }

    public ResponseMessage getSortedTasks(String datetimeStr, String dateType) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            Map<String, List<Task>> tasksByStatus = new HashMap<>();
            LocalDateTime dateTime = Util.getLocalDateTimeFromString(datetimeStr, dateType);
            List<Board> boardList = boardService.getBoards();
            List<Task> taskList = taskService.getTasks(dateTime);

            for (Board board : boardList) {
                if (board.getName() == null || board.getName().isEmpty()) continue;

                List<Task> tasks = new ArrayList<>();
                for (Task task : taskList) {
                    if (task.getStatus() != null && task.getStatus().equalsIgnoreCase(board.getName())) tasks.add(task);
                }

                if (!tasks.isEmpty()) tasksByStatus.put(board.getName(), tasks);
            }

            responseMessage.setOkStatus();
            responseMessage.setBoardList(boardList);
            responseMessage.setTasksByStatus(tasksByStatus);
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setError(e.getMessage());
        }
        return responseMessage;
    }

    public ResponseMessage getSimpleTasks(String datetimeStr, String dateType) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            LocalDateTime dateTime = Util.getLocalDateTimeFromString(datetimeStr, dateType);
            List<Task> taskList = taskService.getTasks(dateTime);

            responseMessage.setOkStatus();
            responseMessage.setTasks(taskList);
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setError(e.getMessage());
        }
        return responseMessage;
    }
}
