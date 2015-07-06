package security.orderpick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import security.orderpick.util.HazelcastUtil;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ReplicatedMap;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@ImportResource({ "classpath:hazelcast-config.xml" })
public class Application{
	
	@Bean
	public HazelcastInstance hazelcast() throws Exception {
		return Hazelcast.newHazelcastInstance();
	}
	
    public static void main(String[] args) throws Throwable {
        ConfigurableApplicationContext cxt =SpringApplication.run(Application.class, args);
        
        HazelcastUtil hz = (HazelcastUtil) cxt.getBean(HazelcastUtil.name);
		ReplicatedMap<String,String> login = hz.getHazelcast().getReplicatedMap("login");
		String key = hz.getHazelcast().getIdGenerator("gen").getPartitionKey();
		login.put(key, "ivan");
		
		System.out.println(login.get(key).toString());
    }

}
