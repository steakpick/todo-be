package com.app.todo.service;

import com.app.todo.domain.Board;
import com.app.todo.domain.Task;
import com.app.todo.repository.BoardRepository;
import com.app.todo.response.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    final
    BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoards() {
        List<Board> boardList = new ArrayList<>();
        try{
            Iterable<Board> all = boardRepository.findAll();
            all.forEach(boardList::add);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return boardList;
    }

    public ResponseMessage addBoard(String board) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            Board boardData = new ObjectMapper().readValue(board, Board.class);
            boardRepository.save(boardData);
            responseMessage.setOkStatus();
        }
        catch (Exception e){
            responseMessage.setError(e.getMessage());
        }

        return responseMessage;
    }
}
