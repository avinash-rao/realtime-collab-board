package com.example.collabboard.service.board;

import com.example.collabboard.dto.board.BoardRequest;
import com.example.collabboard.dto.board.BoardResponse;
import com.example.collabboard.dto.list.ListRequest;
import com.example.collabboard.dto.list.ListResponse;

import java.util.List;

public interface BoardService {
    // Board CRUD
    BoardResponse createBoard(BoardRequest request, String userId);
    BoardResponse getBoardById(String boardId);
    List<BoardResponse> getBoardsForUser(String userId);
    BoardResponse updateBoard(String boardId, BoardRequest request);
    void deleteBoard(String boardId);

    // List Operations
    ListResponse addListToBoard(String boardId, ListRequest request);
    ListResponse updateListInBoard(String boardId, String listId, ListRequest request);
    void deleteListFromBoard(String boardId, String listId);
}
