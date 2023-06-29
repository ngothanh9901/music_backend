package com.example.mediamarkbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindingSongDTO {
    String name;
    String abumName;
    boolean user ;
    String category;
}
