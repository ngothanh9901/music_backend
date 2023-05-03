package com.example.mediamarkbe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SongResponse {
    private Long id;
    private String name;
    private String description;
    private String avatarLink;
    private String musicLink;
}
