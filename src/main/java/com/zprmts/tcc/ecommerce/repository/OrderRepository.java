package com.zprmts.tcc.ecommerce.repository;

import com.zprmts.tcc.ecommerce.domain.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUser_Email(String email, Pageable pageable);
}
