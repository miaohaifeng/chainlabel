package com.happy.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.happy.video.mapper")
public class SmallVideoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmallVideoApplication.class, args);
	}

}

