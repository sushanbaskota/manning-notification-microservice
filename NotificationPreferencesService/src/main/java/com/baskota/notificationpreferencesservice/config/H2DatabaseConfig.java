package com.baskota.notificationpreferencesservice.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2DatabaseConfig {
    /**
     * this is to access the same in-memory H2 database from multiple Spring Boot applications.
     *
     * @return
     * @throws Exception
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws Exception {
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");

    }
}
