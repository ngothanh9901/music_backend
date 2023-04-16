package com.example.mediamarkbe.service.impl;

import com.example.mediamarkbe.dto.SongPayload;
import com.example.mediamarkbe.dto.SongResponse;
import com.example.mediamarkbe.model.Album;
import com.example.mediamarkbe.model.Song;
import com.example.mediamarkbe.respository.AlbumRepository;
import com.example.mediamarkbe.respository.SongRepository;
import com.example.mediamarkbe.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    private final AlbumRepository albumRepository;
    @Override
    public ResponseEntity<SongResponse> addSong(SongPayload songPayload) {
        Song song = SongPayload.convertToModel(songPayload);
        System.out.println(song.getId());
        SongResponse songResponse = mapSongToDto(songRepository.save(song));
        if (song.getId() != null){
            return ResponseEntity.ok().body(songResponse);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(songResponse);
    }

    @Override
    public ResponseEntity<?> deleteSong(Long songId) {
        songRepository.deleteById(songId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<SongResponse> addSongToAlbum(Long songId, Long albumId) {
        Song song = songRepository.findById(songId).get();
        Album album = albumRepository.findById(albumId).get();
        album.getSongs().add(song);
        albumRepository.save(album);
        return ResponseEntity.ok().body(mapSongToDto(song));
    }

    public ResponseEntity<?> deleteSongFromAlbum(Long songId, Long albumId){
        Song song = songRepository.findById(songId).get();
        Album album = albumRepository.findById(albumId).get();
        album.getSongs().remove(song);
        return ResponseEntity.noContent().build();
    }
    private SongResponse mapSongToDto(Song song){
        SongResponse songResponse = new SongResponse();
        songResponse.setName(song.getName());
        songResponse.setDescription(song.getDescription());
        songResponse.setAvatarLink(song.getAvatarLink());
        return songResponse;
    }
}
