package com.example.collabboard.repository;

import com.example.collabboard.model.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends MongoRepository<Board, String> {

    List<Board> findByMembersContaining(String userId);
}
