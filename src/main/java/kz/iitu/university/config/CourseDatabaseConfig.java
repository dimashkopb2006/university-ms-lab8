package kz.iitu.university.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "kz.iitu.university.course.repository",
        entityManagerFactoryRef = "courseEntityManagerFactory",
        transactionManagerRef = "courseTransactionManager"
)
public class CourseDatabaseConfig {

    @Bean
    @ConfigurationProperties("app.datasource.course")
    public DataSource courseDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean courseEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("courseDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("kz.iitu.university.course.entity")
                .persistenceUnit("course")
                .build();
    }

    @Bean
    public PlatformTransactionManager courseTransactionManager(
            @Qualifier("courseEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
