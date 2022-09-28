package hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Map;

public class MyEntryProcessorMain {
	
	public static void main(String[] args) {
		
		HazelcastInstance     hz  = Hazelcast.newHazelcastInstance();
		IMap<String, Integer> map = hz.getMap("myMap");
		
		map.set("test1", 10);
		map.set("test2", 20);
		map.set("test3", 30);
		
		Map<String, Object> res = map.executeOnEntries(new MyEntryProcessor());
		
		for (Map.Entry<String, Integer> e : map.entrySet()) {
			System.out.println("Name : " + e.getKey() + " New Value is : " + e.getValue());
		}
	}
	
}
