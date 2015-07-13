package security.orderpick.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
@ImportResource({ "classpath:hazelcast-config.xml" })
public class HazelcastConfig {

	@Bean
	public HazelcastInstance hazelcast() throws Exception {
		return Hazelcast.newHazelcastInstance();
	}
}
