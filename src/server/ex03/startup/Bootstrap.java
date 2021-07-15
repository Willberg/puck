package server.ex03.startup;

import server.ex03.connector.HttpConnector;

public class Bootstrap {
	public static void main(String[] args) {
		HttpConnector connector = new HttpConnector();
		connector.start();
	}
}
