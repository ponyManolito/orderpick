package security.orderpick.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ReplicatedMap;

@Component(HazelcastUtil.name)
public class HazelcastUtil {
	
	public static final String name = "hazelcastUtil";
	@Autowired
	private HazelcastInstance hazelcast;
	
	public HazelcastUtil() {
		//hazelcast.getMap(arg0)
	}
	
	
	public HazelcastInstance getHazelcast() {
		return hazelcast;
	}


	public void setHazelcast(HazelcastInstance hazelcast) {
		this.hazelcast = hazelcast;
	}


	public static void main(String[] args) {
		
		HazelcastUtil hz = new HazelcastUtil();
		ReplicatedMap<String,String> login = hz.getHazelcast().getReplicatedMap("login");
		
		login.put(hz.getHazelcast().getIdGenerator("gen").getPartitionKey(), "ivan");
		
		System.out.println(login.toString());
		
	}
}
