package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Class {@link Application}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 23.03.16
 */

@SpringBootApplication
@ComponentScan({"com"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
