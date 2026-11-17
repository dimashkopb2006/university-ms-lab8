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
        basePackages = "kz.iitu.university.grade.repository",
        entityManagerFactoryRef = "gradeEntityManagerFactory",
        transactionManagerRef = "gradeTransactionManager"
)
public class GradeDatabaseConfig {

    @Bean
    @ConfigurationProperties("app.datasource.grade")
    public DataSource gradeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean gradeEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("gradeDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("kz.iitu.university.grade.entity")
                .persistenceUnit("grade")
                .build();
    }

    @Bean
    public PlatformTransactionManager gradeTransactionManager(
            @Qualifier("gradeEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
