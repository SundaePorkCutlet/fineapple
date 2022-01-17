package kr.or.fineapple;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan 
@SpringBootApplication
public class FineappleApplication {	
	public static void main(String[] args) {
		SpringApplication.run(FineappleApplication.class, args);
		
	}
}
