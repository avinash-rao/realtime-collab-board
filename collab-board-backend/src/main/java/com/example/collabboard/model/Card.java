package com.example.collabboard.model;

import com.example.collabboard.model.subdocuments.ChecklistItem;
import com.example.collabboard.model.subdocuments.Comment;
import com.example.collabboard.model.subdocuments.Label;
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
@Document(collection = "cards")
public class Card {

    @Id
    private String id;

    private String title;
    private String description;

    private String boardId;
    private String listId;

    private List<Label> labels = new ArrayList<>();
    private List<ChecklistItem> checklist = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    private Instant dueDate;
    private Instant position;

    private List<String> assignees = new ArrayList<>();

    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
}
