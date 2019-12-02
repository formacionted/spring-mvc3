package com.telefonica.jee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefonica.jee.model.ProductSize;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long>{

}
