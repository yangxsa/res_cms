package com.yaa.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.yaa.cms.dao")
public class ResCmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResCmsApplication.class, args);
	}

}
