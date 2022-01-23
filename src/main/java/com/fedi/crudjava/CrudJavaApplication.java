package com.fedi.crudjava;

import com.fedi.crudjava.controllers.ArticleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class CrudJavaApplication {

    public static void main(String[] args) {
        new File(ArticleController.uploadDirectory).mkdir();
        SpringApplication.run(CrudJavaApplication.class, args);
    }

}
