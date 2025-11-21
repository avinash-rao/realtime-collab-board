package com.example.collabboard.repository;

import com.example.collabboard.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends MongoRepository<Card, String> {

    List<Card> findByBoardId(String boardId);   //required to load all cards when user opens the board

    List<Card> findByListId(String listId);     //required to load cards for a specific list - needed for drag-and-drop logic
}
