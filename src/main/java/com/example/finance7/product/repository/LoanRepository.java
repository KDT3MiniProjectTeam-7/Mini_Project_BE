package com.example.finance7.product.repository;

import com.example.finance7.product.entity.Loan;
import com.example.finance7.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Page<Product> findAllByProductNameContaining(String productName, Pageable pageable);
}
