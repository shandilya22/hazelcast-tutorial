package hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.quorum.QuorumException;

public class MyHazelcastClient {
	
	public static void main(String[] args) {
		
		HazelcastInstance     hz    = HazelcastClient.newHazelcastClient();
		IMap<Integer, String> myMap = hz.getMap("myMap");
		
		try {
			myMap.set(1, "sample");
		} catch (QuorumException exception) {
			System.out.println("Exception in client : " + exception);
		}
	}
	
}
