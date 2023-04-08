package com.example.mediamarkbe.controller;

import com.example.mediamarkbe.dto.SongPayload;
import com.example.mediamarkbe.dto.SongResponse;
import com.example.mediamarkbe.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/song")
public class SongController {
    private final SongService songService;

    @PostMapping("/create")
    public ResponseEntity<SongResponse> createSong(@RequestBody SongPayload songPayload){
        return songService.addSong(songPayload);
    }
    @GetMapping("/delete")
    public ResponseEntity<?> deleteSong(@RequestParam Long songId){
        return songService.deleteSong(songId);
    }
}
