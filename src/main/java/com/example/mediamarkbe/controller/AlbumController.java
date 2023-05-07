package com.example.mediamarkbe.controller;

import com.example.mediamarkbe.dto.AlbumPayload;
import com.example.mediamarkbe.dto.PlaylistResponse;
import com.example.mediamarkbe.dto.SongResponse;
import com.example.mediamarkbe.service.AlbumService;
import com.example.mediamarkbe.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/album")
public class AlbumController {
    private final AlbumService albumService;
    private final SongService songService;
    @PostMapping
    public ResponseEntity<PlaylistResponse> createPlaylist(@RequestBody AlbumPayload albumPayload){
        return albumService.createAlbum(albumPayload);
    }
    @GetMapping
    public ResponseEntity<List<PlaylistResponse>> findPlaylistsByUser(){
        return albumService.findAlbumsByUser();
    }
    @DeleteMapping
    public ResponseEntity<?> deletePlaylist(@RequestParam Long playlistId){
        return albumService.deleteAlbum(playlistId);
    }
    @PutMapping
    public ResponseEntity<?> editPlaylist(@RequestBody AlbumPayload albumPayload){
        return albumService.editAlbum(albumPayload);
    }

    @GetMapping("/addSong")
    public ResponseEntity<SongResponse> addSongToPlaylist(@RequestParam Long songId, @RequestParam Long playlistId){
        return songService.addSongToAlbum(songId, playlistId);
    }

    @DeleteMapping("/deleteSong")
    public ResponseEntity<SongResponse> deleteSongFromPlaylist(@RequestParam Long songId, @RequestParam Long playlistId){
        return songService.deleteSongFromAlbum(songId, playlistId);
    }
    @GetMapping("/songs")
    public ResponseEntity<List<SongResponse>> findSongsInPlaylist(@RequestParam Long playlistId){
        return songService.findSongsInAlbum(playlistId);
    }
}
