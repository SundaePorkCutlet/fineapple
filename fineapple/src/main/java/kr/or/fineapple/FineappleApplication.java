package kr.or.fineapple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class FineappleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FineappleApplication.class, args);
	}

}
