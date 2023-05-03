package com.example.mediamarkbe.respository.customRepository;

import com.example.mediamarkbe.dto.FindingSongDTO;
import com.example.mediamarkbe.model.Album;
import com.example.mediamarkbe.model.Song;
import com.example.mediamarkbe.respository.customRepository.impl.CustomizedSongRepositoryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomizedSongRepository {
    Page<Song> findSong(FindingSongDTO payload, Pageable pageable, Album album);
}
