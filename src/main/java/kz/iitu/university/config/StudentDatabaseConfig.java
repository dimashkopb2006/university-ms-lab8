package kz.iitu.university.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

// Everything for student_db: DataSource -> EntityManagerFactory -> TransactionManager
@Configuration
@EnableJpaRepositories(
        basePackages = "kz.iitu.university.student.repository",
        entityManagerFactoryRef = "studentEntityManagerFactory",
        transactionManagerRef = "studentTransactionManager"
)
public class StudentDatabaseConfig {

    @Primary
    @Bean
    @ConfigurationProperties("app.datasource.student")
    public DataSource studentDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean studentEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("studentDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("kz.iitu.university.student.entity")
                .persistenceUnit("student")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager studentTransactionManager(
            @Qualifier("studentEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
