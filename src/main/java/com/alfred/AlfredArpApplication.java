package com.alfred;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = {"com.alfred.system.mapper","com.alfred.business.mapper"})
@EnableCaching
public class AlfredArpApplication {

  public static void main(String[] args) {
    SpringApplication.run(AlfredArpApplication.class, args);
  }

}
