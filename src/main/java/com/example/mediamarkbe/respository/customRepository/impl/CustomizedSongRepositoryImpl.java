package com.example.mediamarkbe.respository.customRepository.impl;

import com.example.mediamarkbe.dto.FindingSongDTO;
import com.example.mediamarkbe.model.Song;
import com.example.mediamarkbe.respository.customRepository.CustomizedSongRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

public class CustomizedSongRepositoryImpl implements CustomizedSongRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private Query buidHqlQueryFindSong(FindingSongDTO payload, Pageable pageable, boolean isCount){
        Map<String, Object> paramMap = new HashMap<>();
        String hqlQuery = "select s from Song s";
        if(isCount){
            hqlQuery = "select count(s) from Song s";
        }
        hqlQuery+=" where 1=1";
        if (StringUtils.isNotBlank(payload.getName())) {
            hqlQuery += "  and ( lower(p.name) like :text )";
            paramMap.put("text", "%"+payload.getName().toLowerCase().trim()+"%");
        }

        if (!isCount && pageable != null && pageable.getSort() != null) {
            hqlQuery += " Order by " +"createdAt"+ " " + "ASC";
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
    public Page<Song> findSong(FindingSongDTO payload, Pageable pageable) {
        long total = (long) buidHqlQueryFindSong(payload,pageable,true).getSingleResult();
        Query query= buidHqlQueryFindSong(payload,pageable,false);
        return new PageImpl<>(query.getResultList(), pageable, total);
    }
}
