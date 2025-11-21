package com.example.collabboard.model.subdocuments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardList {

    private String id;
    private String title;
    private Integer position;
}
