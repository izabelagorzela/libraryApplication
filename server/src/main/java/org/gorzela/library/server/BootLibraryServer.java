package org.gorzela.library.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Iza on 2017-07-23.
 */

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class BootLibraryServer {

    public static void main(String[] args) {

        SpringApplication.run(BootLibraryServer.class, args);
    }
}
