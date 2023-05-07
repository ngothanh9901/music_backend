package com.example.mediamarkbe.dto;

import com.example.mediamarkbe.model.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumPayload {
    private Long id;
    private String name;
    private Long userId;
    private List<Long> songsId;
    private String avatarUrl;
    public static Album convertToModel(AlbumPayload albumPayload){
        Album album = new Album();
        album.setId(albumPayload.getId());
        album.setName(albumPayload.getName());
        album.setAvatarUrl(albumPayload.getAvatarUrl());
        return album;
    }
}
