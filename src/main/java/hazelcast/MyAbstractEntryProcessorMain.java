package hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Map;

public class MyAbstractEntryProcessorMain {
	
	public static void main(String[] args) {
		
		HazelcastInstance     hz    = Hazelcast.newHazelcastInstance();
		IMap<String, Integer> myMap = hz.getMap("myMap");
		
		myMap.set("test1", 30);
		myMap.set("test2", 40);
		
		myMap.executeOnEntries(new MyAbstractEntryProcessor());
		
		for (Map.Entry<String, Integer> e : myMap.entrySet()) {
			System.out.println("Key : " + e.getKey() + " Value : " + e.getValue());
		}
	}
}
