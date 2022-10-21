package com.cleancode.cleancodedbimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@EnableTransactionManagement // +++ added annotation
@EnableJpaRepositories(basePackages = "com.cleancode.cleancodedbimpl", entityManagerFactoryRef = "emf")
@PropertySource("clean-code-db-impl.db.application.properties")
@ComponentScan({ "com.cleancode.cleancodedbimpl" })
public class H2JpaConfig {

    @Autowired
    private Environment env;

    private static final Logger LOGGER = Logger.getLogger(H2JpaConfig.class.getName());

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        return new LocalSessionFactoryBean();
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        LOGGER.log(Level.OFF,"Setting transaction manager.");
        // setup transaction manager based on session factory
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}