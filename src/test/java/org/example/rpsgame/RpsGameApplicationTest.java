package org.example.rpsgame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RpsGameApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        // Verifies that the application context loads successfully
    }

    @Test
    void gameServiceBeanExists() {
        Object gameService = applicationContext.getBean(GameService.class);
        assertNotNull(gameService, "GameService bean should exist in the application context");
    }

    @Test
    void applicationPropertiesLoaded() {
        String appName = applicationContext.getEnvironment().getProperty("spring.application.name");
        assertNotNull(appName, "Application name property should be loaded");
        assertEquals("rpsGame", appName, "Application name should be 'rpsGame'");
    }
}