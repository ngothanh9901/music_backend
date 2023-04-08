package com.example.mediamarkbe.service.impl;

import com.example.mediamarkbe.dto.AlbumPayload;
import com.example.mediamarkbe.dto.PlaylistResponse;
import com.example.mediamarkbe.dto.SongPayload;
import com.example.mediamarkbe.dto.SongResponse;
import com.example.mediamarkbe.model.Album;
import com.example.mediamarkbe.model.Song;
import com.example.mediamarkbe.respository.AlbumRepository;
import com.example.mediamarkbe.respository.SongRepository;
import com.example.mediamarkbe.respository.UserRepository;
import com.example.mediamarkbe.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    @Override
    public ResponseEntity<PlaylistResponse> createAlbum(AlbumPayload albumPayload) {
        if(albumPayload.getUserId() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Album album = AlbumPayload.convertToModel(albumPayload);
        album.setUser(userRepository.findById(albumPayload.getUserId()).get());
        PlaylistResponse playlistResponse = mapAlbumToDTO(albumRepository.saveAndFlush(album));
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistResponse);
    }

    @Override
    public ResponseEntity<List<PlaylistResponse>> findAlbumsByUser(Long userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok().body(albumRepository
                .findAllByUserId(userId, pageable)
                .get().map(album -> mapAlbumToDTO(album))
                .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<?> deleteAlbum(Long albumId) {
        albumRepository.deleteById(albumId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> editAlbum(AlbumPayload albumPayload) {
        Album album = AlbumPayload.convertToModel(albumPayload);
        Album res = albumRepository.save(album);
        return ResponseEntity.noContent().build();
    }

    private PlaylistResponse mapAlbumToDTO(Album album){
        PlaylistResponse playlistResponse = new PlaylistResponse();
        playlistResponse.setName(album.getName());
        playlistResponse.setId(album.getId());
        playlistResponse.setUserId(album.getUser().getId());
        playlistResponse.setCreated_at(album.getCreatedAt());
        playlistResponse.setUpdated_at(album.getUpdatedAt());
        return playlistResponse;
    }
}
