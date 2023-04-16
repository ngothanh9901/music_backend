package com.example.mediamarkbe.dto;

import com.example.mediamarkbe.model.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongPayload {
    private Long id;
    private String name;
    private String description;
    private String avatarLink;
    public static Song convertToModel(SongPayload songPayload){
        Song song = new Song();
        song.setId(songPayload.getId());
        song.setName(songPayload.getName());
        song.setDescription(songPayload.getDescription());
        song.setAvatarLink(songPayload.getAvatarLink());
        return song;
    }
}
