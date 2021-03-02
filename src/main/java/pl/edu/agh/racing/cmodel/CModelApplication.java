package pl.edu.agh.racing.cmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import pl.edu.agh.racing.cmodel.swagger.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class CModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CModelApplication.class, args);
    }

}
