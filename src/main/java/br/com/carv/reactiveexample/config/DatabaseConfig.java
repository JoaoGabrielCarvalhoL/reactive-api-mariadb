package br.com.carv.reactiveexample.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.util.logging.Logger;

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfig {

    private final Logger logger = Logger.getLogger(DatabaseConfig.class.getCanonicalName());

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        logger.info("Initializing Database Configuration...");

        ConnectionFactoryInitializer connectionFactoryInitializer =
                    new ConnectionFactoryInitializer();

            connectionFactoryInitializer.
                    setConnectionFactory(connectionFactory);

            connectionFactoryInitializer.
                    setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
            return connectionFactoryInitializer;
    }
}
