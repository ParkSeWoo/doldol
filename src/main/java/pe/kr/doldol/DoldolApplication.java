package pe.kr.doldol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DoldolApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoldolApplication.class, args);
	}

}
