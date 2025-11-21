package com.example.collabboard.model.subdocuments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private String id;
    private String userId;
    private String text;
    private Instant createdAt = Instant.now();
}
