package DroneDeliveryService.Ajua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@EnableScheduling
@EnableTransactionManagement
public class AjuaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AjuaApplication.class, args);
	}

}
