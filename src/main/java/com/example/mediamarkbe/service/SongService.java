package com.example.mediamarkbe.service;

import com.example.mediamarkbe.dto.SongPayload;
import com.example.mediamarkbe.dto.SongResponse;
import org.springframework.http.ResponseEntity;

public interface SongService {
    ResponseEntity<SongResponse> addSong(SongPayload songPayload);
    ResponseEntity<?> deleteSong(Long songId);

    ResponseEntity<SongResponse> addSongToAlbum(Long songId, Long albumId);

    ResponseEntity<?> deleteSongFromAlbum(Long songId, Long albumId);
}
