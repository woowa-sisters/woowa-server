package com.woowaSisters.woowaSisters.config;

//import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {
        /*"com.hangangFlow.woowaSisters.domain.user",
        "com.hangangFlow.woowaSisters.domain.park",
        "com.hangangFlow.woowaSisters.domain.bookmark",
        "com.hangangFlow.woowaSisters.domain.community",
        "com.hangangFlow.woowaSisters.domain.likes",
        "com.hangangFlow.woowaSisters.domain.test",*/
        "com.woowaSisters.woowaSisters.domain.meeting"
})
public class JpaConfig {
    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                .url("jdbc:mariadb://solux-db.c4omdoqvmmyi.ap-northeast-2.rds.amazonaws.com:3306/woowa-db")
                .username("admin")
                .password("soluxpassword")
                .driverClassName("org.mariadb.jdbc.Driver") // MariaDB의 경우 org.mariadb.jdbc.Driver를 사용합니다.
                .build();
    }

//    spring.datasource.url=jdbc:mariadb://solux-db.c4omdoqvmmyi.ap-northeast-2.rds.amazonaws.com:3306/woowa-db?autoReconnect=true
//    spring.datasource.username=admin
//    spring.datasource.password=soluxpassword

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(
                /*"com.hangangFlow.hangangFlow.domain.user",
                "com.hangangFlow.hangangFlow.domain.park",
                "com.hangangFlow.hangangFlow.domain.bookmark",
                "com.hangangFlow.hangangFlow.domain.community",
                "com.hangangFlow.hangangFlow.domain.likes",
                "com.hangangFlow.hangangFlow.domain.test"*/
                "com.woowaSisters.woowaSisters.domain.meeting"
        );

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect"); // MariaDB Dialect
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }


    // 트랜잭션 매니저 설정
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}




