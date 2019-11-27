package com.switchfully.shoppr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfig {

    @Value("${spring.datasource.url:'jdbc:h2:mem:testDb'}")
    private String dbUrl;

//    @Bean
//    public DataSource dataSource() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(dbUrl);
//        config.setDriverClassName("org.postgresql.Driver");
//        return new HikariDataSource(config);
//    }
}
