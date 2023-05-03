package com.example.mediamarkbe.service;

import com.example.mediamarkbe.dto.FindingSongDTO;
import com.example.mediamarkbe.dto.SongPayload;
import com.example.mediamarkbe.dto.SongResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SongService {
    ResponseEntity<SongResponse> addSong(SongPayload songPayload);
    ResponseEntity<?> deleteSong(Long songId);

    ResponseEntity<SongResponse> addSongToAlbum(Long songId, Long albumId);

    ResponseEntity<?> deleteSongFromAlbum(Long songId, Long albumId);
    List<SongResponse> findSong(FindingSongDTO payload);

    ResponseEntity<List<SongResponse>> findSongsInAlbum(Long albumId);
}
