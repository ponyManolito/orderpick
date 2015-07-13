package security.orderpick.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

@Component(HazelcastUtil.name)
public class HazelcastUtil {

	public static final String name = "hazelcastUtil";

	@Autowired
	private HazelcastInstance hazelcast;

	public HazelcastUtil() {}

	public HazelcastInstance getHazelcast() {
		return hazelcast;
	}

	public void setHazelcast(HazelcastInstance hazelcast) {
		this.hazelcast = hazelcast;
	}
}
