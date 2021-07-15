package server.ex03.util;

import java.util.concurrent.ConcurrentHashMap;

public class StringManager {
	private static ConcurrentHashMap<String, StringManager> managers = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, String> stringMap;

	private StringManager() {
		this.stringMap = new ConcurrentHashMap<>();
	}

	public synchronized static StringManager getManager(String packageName) {
		StringManager manager = managers.get(packageName);
		if (manager == null) {
			manager = new StringManager();
			managers.put(packageName, manager);
		}
		return manager;
	}

	private String getPackageName(String key) {
		int idx = key.lastIndexOf(".");
		if (idx > 0) {
			return key.substring(0, idx);
		}
		return null;
	}

	public String getString(String key) {
		String packageName = this.getPackageName(key);
		if (packageName != null) {
			StringManager manager = getManager(packageName);
			return manager.stringMap.get(key);
		}
		return null;
	}

	public void putString(String key, String value) {
		String packageName = this.getPackageName(key);
		if (packageName != null) {
			StringManager manager = getManager(packageName);
			manager.stringMap.put(key, value);
		}
	}
}
