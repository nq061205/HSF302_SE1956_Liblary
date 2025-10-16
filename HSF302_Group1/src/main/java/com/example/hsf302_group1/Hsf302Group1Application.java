package com.example.hsf302_group1;

import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.repository.UserRepository;
import com.example.hsf302_group1.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.time.LocalDate;


@SpringBootApplication
public class Hsf302Group1Application {

    public static void main(String[] args) {
        SpringApplication.run(Hsf302Group1Application.class, args);
    }

}
