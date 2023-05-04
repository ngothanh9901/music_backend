package com.example.mediamarkbe.respository.customRepository.impl;

import com.example.mediamarkbe.dto.FindingSongDTO;
import com.example.mediamarkbe.model.Album;
import com.example.mediamarkbe.model.Song;
import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.respository.UserRepository;
import com.example.mediamarkbe.respository.customRepository.CustomizedSongRepository;
import com.example.mediamarkbe.security.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

public class CustomizedSongRepositoryImpl implements CustomizedSongRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private Query buidHqlQueryFindSong(FindingSongDTO payload, Pageable pageable, Album album,boolean isCount){
        Map<String, Object> paramMap = new HashMap<>();

        String hqlQuery = "select s from Song s";

        if(isCount && album ==null){
            hqlQuery = "select count(s) from Song s";
        }else if (isCount && album != null){
            hqlQuery = "select count(s) FROM Song s JOIN s.albums a ";
        }
        if(!isCount && album!=null){
            hqlQuery = "select s FROM Song s JOIN s.albums a ";
        }
        hqlQuery+=" where 1=1";
        if (StringUtils.isNotBlank(payload.getName())) {
            hqlQuery += "  and ( lower(s.name) like :text )";
            paramMap.put("text", "%"+payload.getName().toLowerCase().trim()+"%");
        }

        if(album!=null){
            hqlQuery += "  and ( a = :album)";
            paramMap.put("album",album);
        }

        if (!isCount && pageable != null && pageable.getSort() != null) {
            hqlQuery += " Order by " +"s.createdAt"+ " " + "ASC";
        }

        Query query = entityManager.createQuery(hqlQuery);
        for (String key : paramMap.keySet()) {
            query.setParameter(key, paramMap.get(key));
        }
        if (!isCount && pageable != null) {
            Integer pageFrom = pageable.getPageNumber() * pageable.getPageSize();
            Integer pageSize = pageable.getPageSize();
            query.setFirstResult(pageFrom);
            query.setMaxResults(pageSize);

        }
        return query;
    }
    @Override
    public Page<Song> findSong(FindingSongDTO payload, Pageable pageable,Album album) {
        long total = (long) buidHqlQueryFindSong(payload,pageable,album,true).getSingleResult();
        Query query= buidHqlQueryFindSong(payload,pageable,album,false);
        return new PageImpl<>(query.getResultList(), pageable, total);
    }
}
