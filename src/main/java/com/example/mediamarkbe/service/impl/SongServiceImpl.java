package com.example.mediamarkbe.service.impl;

import com.example.mediamarkbe.dto.FindingSongDTO;
import com.example.mediamarkbe.dto.ResponseObject;
import com.example.mediamarkbe.dto.SongPayload;
import com.example.mediamarkbe.dto.SongResponse;
import com.example.mediamarkbe.model.Album;
import com.example.mediamarkbe.model.Song;
import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.respository.AlbumRepository;
import com.example.mediamarkbe.respository.SongRepository;
import com.example.mediamarkbe.respository.UserRepository;
import com.example.mediamarkbe.security.UserPrincipal;
import com.example.mediamarkbe.service.SongService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
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

    @Override
    public ResponseObject<SongResponse> findSong(FindingSongDTO payload, Pageable payPageable) {
        Album album = null;
        if(StringUtils.isNotBlank(payload.getAbumName())){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            User user = userRepository.findById(userPrincipal.getId()).get();
             album = albumRepository.findByUserAndName(user,payload.getName());
        }
        Page<Song> data = songRepository.findSong(payload,payPageable,album);
        List<SongResponse> content = data.getContent().stream().map(s->mapSongToDto(s)).collect(Collectors.toList());
        ResponseObject<SongResponse> responseObject = new ResponseObject<>(content,data.getNumber()+1,data.getSize(),
                data.getTotalElements(),data.getTotalPages(),data.isLast());

        return responseObject;
    }

    private SongResponse mapSongToDto(Song song){
        SongResponse songResponse = new SongResponse();
        songResponse.setId(song.getId());
        songResponse.setName(song.getName());
        songResponse.setDescription(song.getDescription());
        songResponse.setAvatarLink(song.getAvatarLink());
        songResponse.setMusicLink(song.getMusicLink());
        return songResponse;
    }
}
