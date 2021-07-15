package server.ex03.util;

import java.util.HashMap;
import java.util.Map;

public final class ParameterMap extends HashMap {
	private boolean locked = false;

	public boolean isLocked() {
		return this.locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Object put(Object key, Object value) {
		if (locked) {
			throw new IllegalStateException("ParameterMap is locked");
		}
		return super.put(key, value);
	}

	public void putAll(Map map) {
		if (locked) {
			throw new IllegalStateException("ParameterMap is locked");
		}
		super.putAll(map);
	}

	public Object remove(Object key) {
		if (locked) {
			throw new IllegalStateException("ParameterMap is locked");
		}
		return super.remove(key);
	}
}
