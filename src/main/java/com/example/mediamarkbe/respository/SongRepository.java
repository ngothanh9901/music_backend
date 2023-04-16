package com.example.mediamarkbe.respository;

import com.example.mediamarkbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
