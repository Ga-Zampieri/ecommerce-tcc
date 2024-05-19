package com.zprmts.tcc.ecommerce.repository;

import com.zprmts.tcc.ecommerce.domain.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long>, JpaSpecificationExecutor<Perfume> {

    List<Perfume> findByIdIn(List<Long> perfumesIds);
}
