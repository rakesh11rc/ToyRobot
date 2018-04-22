import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.myprojects.toyrobot.api", "com.myprojects.toyrobot.apiController",
        "com.myprojects.toyrobot.service", "com.myprojects.toyrobot.config"})
public class ToyRobot {

    public static void main(String[] args) {
        SpringApplication.run(ToyRobot.class, args);
    }
}
