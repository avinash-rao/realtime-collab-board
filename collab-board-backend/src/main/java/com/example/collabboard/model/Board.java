package com.example.collabboard.model;

import com.example.collabboard.model.subdocuments.BoardList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "boards")
public class Board {

    @Id
    private String id;

    private String title;
    private String description;

    // Embedded lists
    private List<BoardList> lists = new ArrayList<>();

    // user IDs
    private List<String> members = new ArrayList<>();

    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
}
