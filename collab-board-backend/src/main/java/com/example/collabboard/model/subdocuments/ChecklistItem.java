package com.example.collabboard.model.subdocuments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistItem {

    private String id;
    private String text;
    private Boolean completed;
}
