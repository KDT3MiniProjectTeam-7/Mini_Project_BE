package com.example.finance7.product.repository;

import com.example.finance7.product.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
