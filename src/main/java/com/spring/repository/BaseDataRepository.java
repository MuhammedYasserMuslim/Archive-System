package com.spring.repository;

import com.spring.model.entity.BaseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseDataRepository extends JpaRepository<BaseData, Byte> {

    @Query("select base.imagesPath from BaseData base")
    String findBaseData();
}
