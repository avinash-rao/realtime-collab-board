package com.example.collabboard.dto.card;

import com.example.collabboard.model.subdocuments.ChecklistItem;
import com.example.collabboard.model.subdocuments.Comment;
import com.example.collabboard.model.subdocuments.Label;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CardResponse {

    private String id;
    private String title;
    private String description;

    private String boardId;
    private String listId;

    private List<Label> labels;
    private List<ChecklistItem> checklist;
    private List<Comment> comments;

    private Instant dueDate;
    private Integer position;

    private List<String> assignees;

    private Instant createdAt;
    private Instant updatedAt;
}
