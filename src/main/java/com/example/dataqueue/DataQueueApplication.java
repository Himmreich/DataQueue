package com.example.dataqueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DataQueueApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DataQueueApplication.class, args);
	}

}
