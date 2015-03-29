package com.mccraftaholics.warpportals.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.mccraftaholics.warpportals.server")
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        Properties serverProperties = loadExternalProperties();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(serverProperties.getProperty("spring.datasource.url", "jdbc:mysql://localhost/warpportals"));
        dataSource.setUsername(serverProperties.getProperty("spring.datasource.username", "warpportals"));
        dataSource.setPassword(serverProperties.getProperty("spring.datasource.password", "developmentpassword"));

        return dataSource;
    }

    private Properties loadExternalProperties() {
        //to load application's properties, we use this class
        Properties serverProperties = new Properties();
        FileInputStream file = null;
        //the base folder is ./, the root of the server.properties file
        String path = "./server.properties";
        //load the file handle for server.properties
        try {
            file = new FileInputStream(path);
            //load all the properties from this file
            serverProperties.load(file);
        } catch (FileNotFoundException e) {
            System.out.println("Using default server properties");
        } catch (IOException e) {
            System.out.println("Using default server properties");
        } finally {
            if (file != null) {
                //we have loaded the properties, so close the file handle
                try {
                    file.close();
                } catch (IOException e) { }
            }
        }
        return serverProperties;
    }

}
