package tz.go.tcra.lims;

import java.time.LocalDateTime;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LimsApplication {

        @PostConstruct
        public void init(){
            TimeZone.setDefault(TimeZone.getTimeZone("EAT"));   // It will set UTC timezone
            System.out.println("LIMS application running in EAT timezone :"+LocalDateTime.now());   // It will print UTC timezone
        }
    
	public static void main(String[] args) {
		SpringApplication.run(LimsApplication.class, args);
	}

}
