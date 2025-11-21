package com.example.collabboard.dto.card;

import lombok.Data;

@Data
public class CardRequest {

    private String title;
    private String description;
    private String listId;
    private Integer position;
}
