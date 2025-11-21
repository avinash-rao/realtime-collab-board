package com.example.collabboard.service.card;

import com.example.collabboard.dto.card.CardRequest;
import com.example.collabboard.dto.card.CardResponse;
import com.example.collabboard.exception.ResourceNotFoundException;
import com.example.collabboard.model.Board;
import com.example.collabboard.model.Card;
import com.example.collabboard.repository.BoardRepository;
import com.example.collabboard.repository.CardRepository;
import com.example.collabboard.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final BoardRepository boardRepository;

    /* =========================================================
       CREATE CARD
     ========================================================= */

    @Override
    public CardResponse createCard(String boardId, CardRequest request) {

        // Verify board exists
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found"));

        // Verify list exists inside board
        boolean listExists = board.getLists().stream()
                .anyMatch(l -> l.getId().equals(request.getListId()));

        if (!listExists) {
            throw new ResourceNotFoundException("List not found in board");
        }

        // Create card entity
        Card card = MapperUtil.toCard(request, boardId);
        card.setCreatedAt(Instant.now());
        card.setUpdatedAt(Instant.now());

        Card saved = cardRepository.save(card);
        return MapperUtil.toCardResponse(saved);
    }


    /* =========================================================
       GET CARDS
     ========================================================= */

    @Override
    public List<CardResponse> getCardsByBoard(String boardId) {
        List<Card> cards = cardRepository.findByBoardId(boardId);

        return cards.stream()
                .map(MapperUtil::toCardResponse)
                .collect(Collectors.toList());
    }


    @Override
    public List<CardResponse> getCardsByList(String listId) {
        List<Card> cards = cardRepository.findByListId(listId);

        return cards.stream()
                .map(MapperUtil::toCardResponse)
                .collect(Collectors.toList());
    }


    /* =========================================================
       UPDATE CARD
     ========================================================= */

    @Override
    public CardResponse updateCard(String cardId, CardRequest request) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));

        card.setTitle(request.getTitle());
        card.setDescription(request.getDescription());
        card.setPosition(request.getPosition());
        card.setUpdatedAt(Instant.now());

        Card saved = cardRepository.save(card);
        return MapperUtil.toCardResponse(saved);
    }


    /* =========================================================
       MOVE CARD (LIST → LIST)
     ========================================================= */

    @Override
    public CardResponse moveCard(String cardId, String targetListId, Integer newPosition) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));

        // Check board exists (optional but safer)
        Board board = boardRepository.findById(card.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("Board not found"));

        // Validate target list exists in board
        boolean listExists = board.getLists().stream()
                .anyMatch(l -> l.getId().equals(targetListId));

        if (!listExists) {
            throw new ResourceNotFoundException("Target list does not exist");
        }

        // Perform movement
        card.setListId(targetListId);
        card.setPosition(newPosition);
        card.setUpdatedAt(Instant.now());

        Card saved = cardRepository.save(card);
        return MapperUtil.toCardResponse(saved);
    }


    /* =========================================================
       DELETE CARD
     ========================================================= */

    @Override
    public void deleteCard(String cardId) {

        if (!cardRepository.existsById(cardId)) {
            throw new ResourceNotFoundException("Card not found");
        }

        cardRepository.deleteById(cardId);
    }
}
