package hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class MyOffloadableEntryProcessorMain {
	
	public static void main(String[] args) {
		
		HazelcastInstance     hz  = Hazelcast.newHazelcastInstance();
		IMap<String, Integer> map = hz.getMap("myMap");
		
		map.set("test1", 100);
		map.set("test2", 200);
		
		map.executeOnEntries(new MyOffloadableEntryProcessor());
	}
}
