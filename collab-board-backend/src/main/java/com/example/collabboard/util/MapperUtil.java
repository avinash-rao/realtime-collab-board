package com.example.collabboard.util;

import com.example.collabboard.dto.board.BoardRequest;
import com.example.collabboard.dto.board.BoardResponse;
import com.example.collabboard.dto.card.CardRequest;
import com.example.collabboard.dto.card.CardResponse;
import com.example.collabboard.dto.list.ListRequest;
import com.example.collabboard.dto.list.ListResponse;
import com.example.collabboard.dto.user.UserRequest;
import com.example.collabboard.dto.user.UserResponse;
import com.example.collabboard.model.Board;
import com.example.collabboard.model.Card;
import com.example.collabboard.model.User;
import com.example.collabboard.model.subdocuments.BoardList;

import java.util.UUID;

public class MapperUtil {

    /* =========================================================
       USER MAPPING
     ========================================================= */

    public static User toUser(UserRequest dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAvatarUrl(dto.getAvatarUrl());
        return user;
    }

    public static UserResponse toUserResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setAvatarUrl(user.getAvatarUrl());
        return res;
    }


    /* =========================================================
       BOARD MAPPING
     ========================================================= */
    public static Board toBoard(BoardRequest dto) {
        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setDescription(dto.getDescription());
        return board;
    }

    public static BoardResponse toBoardResponse(Board board) {
        BoardResponse res = new BoardResponse();
        res.setId(board.getId());
        res.setTitle(board.getTitle());
        res.setDescription(board.getDescription());
        res.setLists(board.getLists());
        res.setMembers(board.getMembers());
        res.setCreatedAt(board.getCreatedAt());
        res.setUpdatedAt(board.getUpdatedAt());
        return res;
    }


    /* =========================================================
       LIST MAPPING
     ========================================================= */
    public static BoardList toBoardList(ListRequest dto) {
        BoardList list = new BoardList();
        list.setId(UUID.randomUUID().toString());
        list.setTitle(dto.getTitle());
        list.setPosition(dto.getPosition());
        return list;
    }

    public static ListResponse toListResponse(BoardList list) {
        ListResponse res = new ListResponse();
        res.setId(list.getId());
        res.setTitle(list.getTitle());
        res.setPosition(list.getPosition());
        return res;
    }

    /* =========================================================
       CARD MAPPING
     ========================================================= */
    public static Card toCard(CardRequest dto, String boardId) {
        Card card = new Card();
        card.setBoardId(boardId);
        card.setListId(dto.getListId());
        card.setTitle(dto.getTitle());
        card.setDescription(dto.getDescription());
        card.setPosition(dto.getPosition());
        return card;
    }

    public static CardResponse toCardResponse(Card card) {
        CardResponse res = new CardResponse();
        res.setId(card.getId());
        res.setTitle(card.getTitle());
        res.setDescription(card.getDescription());
        res.setBoardId(card.getBoardId());
        res.setListId(card.getListId());
        res.setLabels(card.getLabels());
        res.setChecklist(card.getChecklist());
        res.setComments(card.getComments());
        res.setDueDate(card.getDueDate());
        res.setPosition(card.getPosition());
        res.setAssignees(card.getAssignees());
        res.setCreatedAt(card.getCreatedAt());
        res.setUpdatedAt(card.getUpdatedAt());
        return res;
    }

}
