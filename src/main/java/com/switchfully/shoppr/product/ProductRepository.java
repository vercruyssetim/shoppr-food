package com.switchfully.shoppr.product;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @RestResource(path = "findByName", rel = "findByName")
    List<Product> findByNameContainingIgnoreCase(@Param("name") String name);

    List<Product> findByProductType(@Param("productType") ProductType productType);
}
