package com.example.demo.hazelcast.mapstore;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.hazelcast.core.MapStore;

public class TestMapStore implements MapStore<String, Object>, Serializable {
	private static final long serialVersionUID = 3627469739017861185L;

	@Override
	public Object load(String key) {
		return "TestMapStore-value-" + key;
	}

	@Override
	public Map<String, Object> loadAll(Collection<String> keys) {
		Map<String, Object> result = new HashMap<>();
		for (String key : keys) {
			result.put(key, "TestMapStore-value-" + key);
		}
		return result;
	}

	@Override
	public Iterable<String> loadAllKeys() {
		return Arrays.asList("1", "2", "3");
	}

	@Override
	public void store(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeAll(Map<String, Object> map) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Collection<String> keys) {
		// TODO Auto-generated method stub

	}

}
