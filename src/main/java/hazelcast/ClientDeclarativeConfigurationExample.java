package hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class ClientDeclarativeConfigurationExample {
	
	public static void main(String[] args) throws FileNotFoundException {
		try {
			ClientDeclarativeConfigurationExample obj = new ClientDeclarativeConfigurationExample();
			obj.initHzServer();
			Thread.sleep(5000);
			obj.initHzClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initHzClient() {
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(5000);
				ClientConfig config = new XmlClientConfigBuilder(
						new FileInputStream("/Users/shandilya/Documents/Programming/hazelcast-tutorial/custom-path/hazelcast-client.xml")).build();
				HazelcastInstance hz = HazelcastClient.newHazelcastClient(config);
				
				System.out.println("Hz client started");
				//				Thread.sleep(5000);
				Map<Integer, String> myMap = hz.getMap("myMap");
				
				System.out.println("map size: " + myMap.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		t.start();
	}
	
	public void initHzServer() {
		Thread t = new Thread(() -> {
			try {
				Config config = new XmlConfigBuilder(
						new FileInputStream(new File("/Users/shandilya/Documents/Programming/hazelcast-tutorial/custom-path/hazelcast.xml"))).build();
				HazelcastInstance     hz    = Hazelcast.newHazelcastInstance(config);
				IMap<Integer, String> myMap = hz.getMap("myMap");
				myMap.put(1, "test");
				myMap.put(1, "test1");
				myMap.set(2, "test2");
				
				for (Map.Entry<Integer, String> e : myMap.entrySet()) {
					System.out.println(e.getKey() + ": " + e.getValue());
				}
				System.out.println("Hz server started");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		t.start();
	}
}
