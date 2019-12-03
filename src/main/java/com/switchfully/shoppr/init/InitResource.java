package com.switchfully.shoppr.init;

import com.switchfully.shoppr.product.ProductRepository;
import com.switchfully.shoppr.product.ProductType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import static com.switchfully.shoppr.product.Product.product;

@RestController
public class InitResource {

    private static Logger LOGGER = LoggerFactory.getLogger(InitResource.class);

    private ProductRepository foodRepository;

    public InitResource(ProductRepository productRepository) {
        this.foodRepository = productRepository;
    }

    @GetMapping(path = "init")
    public void init() throws IOException {
        foodRepository.deleteAll();

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());

        Stream.of(resolver.getResources("classpath:food/*"))
                .peek(r -> LOGGER.info("Loading " + r.getFilename()))
                .forEach(r -> loadFood(r, ProductType.valueOf(r.getFilename())));

        LOGGER.info("initialisation succeeded!!");
    }

    private void loadFood(Resource resource, ProductType productType) {
        try (InputStream inputStream = resource.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            reader
                    .lines()
                    .map(v -> product(v, productType))
                    .forEach(v -> foodRepository.save(v));
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
