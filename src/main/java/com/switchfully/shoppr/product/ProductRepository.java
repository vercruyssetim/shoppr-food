package com.switchfully.shoppr.product;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findByNameContaining(@Param("name") String name);

    List<Product> findByProductType(@Param("productType") ProductType productType);
}
