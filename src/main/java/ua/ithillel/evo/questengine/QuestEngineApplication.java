package ua.ithillel.evo.questengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class QuestEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestEngineApplication.class, args);
    }
}
