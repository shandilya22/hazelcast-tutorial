package hazelcast;

import com.hazelcast.map.EntryBackupProcessor;
import com.hazelcast.map.EntryProcessor;

import java.util.Map.Entry;

public class MyEntryProcessor implements EntryProcessor<String, Integer>, EntryBackupProcessor<String, Integer> {
	
	public void processBackup(Entry<String, Integer> entry) {
		
		int age = entry.getValue() + 10;
		entry.setValue(age);
	}
	
	public Object process(Entry<String, Integer> entry) {
		
		int age = entry.getValue() + 10;
		entry.setValue(age);
		return age;
	}
	
	public EntryBackupProcessor<String, Integer> getBackupProcessor() {
		
		return MyEntryProcessor.this;
	}
	
}
