package com.example.mediamarkbe.respository;

import com.example.mediamarkbe.model.Album;
import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.respository.customRepository.CustomizedAlbumRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long>, CustomizedAlbumRepository {
    Page<Album> findAllByUserId(Long userId, Pageable pageable);
    Album findByUserAndName(User user,String name);
}
