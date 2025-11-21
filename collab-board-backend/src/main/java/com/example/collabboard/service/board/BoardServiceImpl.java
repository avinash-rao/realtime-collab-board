package com.example.collabboard.service.board;

import com.example.collabboard.dto.board.BoardRequest;
import com.example.collabboard.dto.board.BoardResponse;
import com.example.collabboard.dto.list.ListRequest;
import com.example.collabboard.dto.list.ListResponse;
import com.example.collabboard.exception.ResourceNotFoundException;
import com.example.collabboard.model.Board;
import com.example.collabboard.model.subdocuments.BoardList;
import com.example.collabboard.repository.BoardRepository;
import com.example.collabboard.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    /* =========================================================
       BOARD CRUD
     ========================================================= */

    @Override
    public BoardResponse createBoard(BoardRequest request, String userId) {

        Board board = MapperUtil.toBoard(request);

        // add creator as member
        board.getMembers().add(userId);

        board.setCreatedAt(Instant.now());
        board.setUpdatedAt(Instant.now());

        Board saved = boardRepository.save(board);
        return MapperUtil.toBoardResponse(saved);
    }

    @Override
    public BoardResponse getBoardById(String boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found"));

        return MapperUtil.toBoardResponse(board);
    }

    @Override
    public List<BoardResponse> getBoardsForUser(String userId) {
        List<Board> boards = boardRepository.findByMembersContaining(userId);
        return boards.stream()
                .map(MapperUtil::toBoardResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BoardResponse updateBoard(String boardId, BoardRequest request) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found"));

        board.setTitle(request.getTitle());
        board.setDescription(request.getDescription());
        board.setUpdatedAt(Instant.now());

        Board saved = boardRepository.save(board);
        return MapperUtil.toBoardResponse(saved);
    }

    @Override
    public void deleteBoard(String boardId) {
        if (!boardRepository.existsById(boardId)) {
            throw new ResourceNotFoundException("Board not found");
        }
        boardRepository.deleteById(boardId);
    }

    /* =========================================================
       LIST OPERATIONS
     ========================================================= */

    @Override
    public ListResponse addListToBoard(String boardId, ListRequest request) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found"));

        BoardList list = MapperUtil.toBoardList(request);
        board.getLists().add(list);

        board.setUpdatedAt(Instant.now());
        boardRepository.save(board);

        return  MapperUtil.toListResponse(list);
    }

    @Override
    public ListResponse updateListInBoard(String boardId, String listId, ListRequest request) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found"));

        Optional<BoardList> found = board.getLists()
                .stream()
                .filter(l -> l.getId().equals(listId))
                .findFirst();

        if (found.isEmpty()) {
            throw new ResourceNotFoundException("List not found in board");
        }

        BoardList list = found.get();
        list.setTitle(request.getTitle());
        list.setPosition(request.getPosition());

        board.setUpdatedAt(Instant.now());
        boardRepository.save(board);

        return MapperUtil.toListResponse(list);
    }

    @Override
    public void deleteListFromBoard(String boardId, String listId) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found"));

        boolean removed = board.getLists().removeIf(l -> l.getId().equals(listId));

        if (!removed) {
            throw new ResourceNotFoundException("List not found in board");
        }

        board.setUpdatedAt(Instant.now());
        boardRepository.save(board);
    }
}
