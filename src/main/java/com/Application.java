package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Maryfrancis Remo Moll
 *
 * This is the root class that is run to start the application
 */
@SpringBootApplication
@CrossOrigin(origins = "http://localhost:9191")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
