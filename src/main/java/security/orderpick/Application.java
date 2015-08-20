package security.orderpick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@ImportResource({ "classpath:hazelcast-config.xml" })
public class Application extends SpringBootServletInitializer{

	@Bean
	public HazelcastInstance hazelcast() throws Exception {
		return Hazelcast.newHazelcastInstance();
	}

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

}
