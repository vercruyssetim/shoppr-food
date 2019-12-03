package com.switchfully.shoppr;

import com.switchfully.shoppr.product.Product;
import com.switchfully.shoppr.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.switchfully.shoppr.product.Product.product;
import static com.switchfully.shoppr.product.ProductType.VEGETABLE;

@DataJpaTest
class ApplicationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void test() {
        productRepository.save(product("Tomato", VEGETABLE));
        Iterable<Product> all = productRepository.findAll();

        System.out.println(all);
    }

}
