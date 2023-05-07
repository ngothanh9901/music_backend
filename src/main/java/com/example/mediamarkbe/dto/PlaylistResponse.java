package com.example.mediamarkbe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;


@Data
@NoArgsConstructor
public class PlaylistResponse {
    private Long id;
    private String name;
    private String avatarUrl;
    private Long userId;
}
