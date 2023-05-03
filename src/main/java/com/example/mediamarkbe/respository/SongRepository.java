package com.example.mediamarkbe.respository;

import com.example.mediamarkbe.model.Song;
import com.example.mediamarkbe.respository.customRepository.CustomizedSongRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long>, CustomizedSongRepository {
    List<Song> findByNameContaining(String name);
}
