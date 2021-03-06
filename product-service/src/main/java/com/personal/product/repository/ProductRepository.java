package com.personal.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
