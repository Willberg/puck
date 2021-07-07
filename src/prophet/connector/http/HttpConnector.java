package prophet.connector.http;

import prophet.core.Connector;

import java.util.Stack;

public class HttpConnector implements Connector {
	protected int minProcessors = 5;
	protected int maxProcessors = 20;


	private Stack processors = new Stack();

	public void setMinProcessors(int minProcessors) {
		this.minProcessors = minProcessors;
	}

	public void setMaxProcessors(int maxProcessors) {
		this.maxProcessors = maxProcessors;
	}


}
