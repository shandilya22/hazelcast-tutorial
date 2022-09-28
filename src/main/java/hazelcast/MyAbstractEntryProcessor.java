package hazelcast;

import com.hazelcast.map.AbstractEntryProcessor;

import java.util.Map.Entry;

public class MyAbstractEntryProcessor extends AbstractEntryProcessor<String, Integer> {
	
	public MyAbstractEntryProcessor() {
		super(false);
	}
	
	public Object process(Entry<String, Integer> entry) {
		
		int value = entry.getValue();
		entry.setValue(value + 10);
		return null;
	}
}
