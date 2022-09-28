package hazelcast;

import com.hazelcast.core.Offloadable;
import com.hazelcast.core.ReadOnly;
import com.hazelcast.map.AbstractEntryProcessor;

import java.util.Map.Entry;

public class MyOffloadableEntryProcessor extends AbstractEntryProcessor<String, Integer> implements Offloadable, ReadOnly {
	
	public Object process(Entry<String, Integer> entry) {
		
		int value = entry.getValue();
		System.out.println("Value is : " + value);
		entry.setValue(value + 10);
		return null;
	}
	
	public String getExecutorName() {
		return OFFLOADABLE_EXECUTOR;
	}
}