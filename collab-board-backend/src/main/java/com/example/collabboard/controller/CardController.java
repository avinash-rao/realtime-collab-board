package com.example.collabboard.controller;

import com.example.collabboard.dto.card.CardRequest;
import com.example.collabboard.dto.card.CardResponse;
import com.example.collabboard.service.card.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;


    /* =========================================================
       CARD CREATION
     ========================================================= */

    @PostMapping("/boards/{boardId}/cards")
    public CardResponse createCard(
            @PathVariable String boardId,
            @Valid @RequestBody CardRequest request
    ) {
        return cardService.createCard(boardId, request);
    }


    /* =========================================================
       CARD FETCHING
     ========================================================= */

    @GetMapping("/boards/{boardId}/cards")
    public List<CardResponse> getCardsByBoard(
            @PathVariable String boardId
    ) {
        return cardService.getCardsByBoard(boardId);
    }

    @GetMapping("/lists/{listId}/cards")
    public List<CardResponse> getCardsByList(
            @PathVariable String listId
    ) {
        return cardService.getCardsByList(listId);
    }


    /* =========================================================
       CARD UPDATE
     ========================================================= */

    @PutMapping("/cards/{cardId}")
    public CardResponse updateCard(
            @PathVariable String cardId,
            @Valid @RequestBody CardRequest request
    ) {
        return cardService.updateCard(cardId, request);
    }


    /* =========================================================
       MOVE CARD
     ========================================================= */

    @PutMapping("/cards/{cardId}/move")
    public CardResponse moveCard(
            @PathVariable String cardId,
            @RequestParam String targetListId,
            @RequestParam Integer newPosition
    ) {
        return cardService.moveCard(cardId, targetListId, newPosition);
    }


    /* =========================================================
       DELETE CARD
     ========================================================= */

    @DeleteMapping("/cards/{cardId}")
    public void deleteCard(
            @PathVariable String cardId
    ) {
        cardService.deleteCard(cardId);
    }
}
