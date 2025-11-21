package com.example.collabboard.dto.board;

import com.example.collabboard.model.subdocuments.BoardList;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class BoardResponse {

    private String id;
    private String title;
    private String description;
    private List<BoardList> lists;
    private List<String> members;

    private Instant createdAt;
    private Instant updatedAt;
}
