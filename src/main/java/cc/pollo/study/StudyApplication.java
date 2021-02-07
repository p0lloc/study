package cc.pollo.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Main entry point
 */
@SpringBootApplication
@EnableMongoRepositories
public class StudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class);
    }

}