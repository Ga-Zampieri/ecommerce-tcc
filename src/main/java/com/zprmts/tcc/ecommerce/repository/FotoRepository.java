package com.zprmts.tcc.ecommerce.repository;

import com.zprmts.tcc.ecommerce.domain.FotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository<FotoEntity, Integer> {

}
