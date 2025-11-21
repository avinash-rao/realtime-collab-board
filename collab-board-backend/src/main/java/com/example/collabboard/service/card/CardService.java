package com.example.collabboard.service.card;

import com.example.collabboard.dto.card.CardRequest;
import com.example.collabboard.dto.card.CardResponse;

import java.util.List;

public interface CardService {

    CardResponse createCard(String boardId, CardRequest request);
    List<CardResponse> getCardsByBoard(String boardId);
    List<CardResponse> getCardsByList(String listId);

    CardResponse updateCard(String cardId, CardRequest request);

    CardResponse moveCard(String cardId, String targetListId, Integer newPosition);

    void deleteCard(String cardId);
}
