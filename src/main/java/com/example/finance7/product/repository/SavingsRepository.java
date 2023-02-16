package com.example.finance7.product.repository;

import com.example.finance7.product.entity.Savings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<Savings, Long> {

    Page<Savings> findAllByProductNameContaining(String productName, Pageable pageable);
}
