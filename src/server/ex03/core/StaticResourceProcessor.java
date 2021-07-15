package server.ex03.core;


import server.ex03.connector.HttpRequest;
import server.ex03.connector.HttpResponse;

import java.io.IOException;

public class StaticResourceProcessor {
	public void process(HttpRequest request, HttpResponse response) {
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
