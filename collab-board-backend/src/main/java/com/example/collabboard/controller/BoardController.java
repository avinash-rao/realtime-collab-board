package com.example.collabboard.controller;

import com.example.collabboard.dto.board.BoardRequest;
import com.example.collabboard.dto.board.BoardResponse;
import com.example.collabboard.dto.list.ListRequest;
import com.example.collabboard.dto.list.ListResponse;
import com.example.collabboard.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /* =========================================================
       BOARD CRUD
     ========================================================= */

    @PostMapping
    public BoardResponse createBoard(
            @RequestParam String userId,
            @Valid @RequestBody BoardRequest request
    ) {
        return boardService.createBoard(request, userId);
    }

    @GetMapping("/{boardId}")
    public BoardResponse getBoardById(@PathVariable String boardId) {
        return boardService.getBoardById(boardId);
    }

    @GetMapping("/user/{userId}")
    public List<BoardResponse> getBoardsForUser(@PathVariable String userId) {
        return boardService.getBoardsForUser(userId);
    }

    @PutMapping("/{boardId}")
    public BoardResponse updateBoard(
            @PathVariable String boardId,
            @Valid @RequestBody BoardRequest request
    ) {
        return boardService.updateBoard(boardId, request);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable String boardId) {
        boardService.deleteBoard(boardId);
    }


    /* =========================================================
       LIST OPERATIONS
     ========================================================= */

    @PostMapping("/{boardId}/lists")
    public ListResponse addList(
            @PathVariable String boardId,
            @RequestBody ListRequest request
    ) {
        return boardService.addListToBoard(boardId, request);
    }

    @PutMapping("/{boardId}/lists/{listId}")
    public ListResponse updateList(
            @PathVariable String boardId,
            @PathVariable String listId,
            @RequestBody ListRequest request
    ) {
        return boardService.updateListInBoard(boardId, listId, request);
    }

    @DeleteMapping("/{boardId}/lists/{listId}")
    public void deleteList(
            @PathVariable String boardId,
            @PathVariable String listId
    ) {
        boardService.deleteListFromBoard(boardId, listId);
    }
}
