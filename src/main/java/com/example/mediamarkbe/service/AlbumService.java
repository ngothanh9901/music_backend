package com.example.mediamarkbe.service;

import com.example.mediamarkbe.dto.AlbumPayload;
import com.example.mediamarkbe.dto.PlaylistResponse;
import com.example.mediamarkbe.dto.SongResponse;
import com.example.mediamarkbe.model.Song;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AlbumService {
    ResponseEntity<List<PlaylistResponse>> findAlbumsByUser();
    ResponseEntity<PlaylistResponse> createAlbum(AlbumPayload albumPayload);

    ResponseEntity<PlaylistResponse> deleteAlbum(Long albumId);

    ResponseEntity<?> editAlbum(AlbumPayload albumPayload);

    ResponseEntity<List<PlaylistResponse>> findAlbumBySong(Long songId);

}
