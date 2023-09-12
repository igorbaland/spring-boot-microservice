package com.bal.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bal.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
