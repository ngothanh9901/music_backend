package com.example.mediamarkbe.respository;

import com.example.mediamarkbe.model.Album;
import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.model.Song;
import com.example.mediamarkbe.respository.customRepository.CustomizedAlbumRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long>, CustomizedAlbumRepository {
    List<Album> findAllByUserId(Long userId);
    Album findByUserAndName(User user,String name);
    Album findByUser(User user);

    @Query("select a.songs from Album a where a.id = ?1")
    List<Song> findSongsByAlbum(Long albumId);

    @Query("select a from Album a " +
            "inner join a.songs s " +
            "where s.id = ?1")
    List<Album> findAlbumBySong(Long songId);
}
