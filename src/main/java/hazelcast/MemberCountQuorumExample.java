package hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.QuorumConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.quorum.QuorumType;

public class MemberCountQuorumExample {
	
	public static void main(String[] args) throws InterruptedException {
		
		QuorumConfig quorumConfig = new QuorumConfig();
		quorumConfig.setName("Two_Nodes_Quorum").setEnabled(true).setSize(2).setType(QuorumType.WRITE);
		
		MapConfig mapConfig = new MapConfig();
		mapConfig.setName("default").setQuorumName("Two_Nodes_Quorum");
		
		Config config = new Config();
		config.addMapConfig(mapConfig);
		config.addQuorumConfig(quorumConfig);
		
		HazelcastInstance hz  = Hazelcast.newHazelcastInstance(config);
		HazelcastInstance hz1 = Hazelcast.newHazelcastInstance(config);
		
		IMap<Integer, String> myMap = hz.getMap("myMap");
		myMap.set(1, "test");
		myMap.set(2, "test1");
		
		hz1.getLifecycleService().shutdown();
		Thread.sleep(3000);
		
		/*try{
			myMap.set(2, "test1");
		}
		catch(QuorumException exception) {
			System.out.println("Exception : "+exception);
		}*/
	}
	
}
