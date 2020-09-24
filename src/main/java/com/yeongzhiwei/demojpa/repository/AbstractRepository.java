package com.yeongzhiwei.demojpa.repository;

import com.yeongzhiwei.demojpa.domain.AbstractEntity;

import org.springframework.data.repository.CrudRepository;

public interface AbstractRepository<T extends AbstractEntity> extends CrudRepository<T, Long> {
    
}

