package com.fedi.crudjava;

import com.fedi.crudjava.controllers.ArticleController;
import com.fedi.crudjava.controllers.ProviderController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class CrudJavaApplication {

    public static void main(String[] args) {
        new File(ArticleController.uploadDirectory).mkdir();
        new File(ProviderController.providersDirectory).mkdir();
        SpringApplication.run(CrudJavaApplication.class, args);
    }

}
