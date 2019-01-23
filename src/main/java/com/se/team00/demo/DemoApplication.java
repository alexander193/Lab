package com.se.team00.demo;

import com.se.team00.demo.entity.StudentEntity;
import com.se.team00.demo.repository.StudentRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    /*@Bean
    ApplicationRunner init(StudentRepository studentRepository){
        return args -> {
            StudentEntity s = new StudentEntity();
            s.setFirstName("Pitchayut");
            s.setLastName("CheeseJa");
            s.setStudentId("B59226422");
            studentRepository.save(s);
        };
    }*/

}

