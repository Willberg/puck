package server.ex03.connector;


import server.ex03.core.ServletProcessor;
import server.ex03.core.StaticResourceProcessor;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpProcessor {
	private HttpRequest request;

	public HttpProcessor(HttpConnector connector) {

	}

	public void process(Socket socket) {
		SocketInputStream input = null;
		OutputStream output = null;
		try {
			input = new SocketInputStream(socket.getInputStream());
			output = socket.getOutputStream();

			request = new HttpRequest(socket.getInputStream());

			HttpResponse response = new HttpResponse(output);
			response.setRequest(request);
			response.setHeader("Server", "my tomcat");

			parseRequest(input);
			parseHeaders(input);

			if (request.getRequestURI().startsWith("/servlet/")) {
				ServletProcessor processor = new ServletProcessor();
				processor.process(request, response);
			} else {
				StaticResourceProcessor processor = new StaticResourceProcessor();
				processor.process(request, response);
			}

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 解析请求行
	private void parseRequest(SocketInputStream input) throws IOException, ServletException {
		HttpRequestLine httpRequestLine = new HttpRequestLine();
		input.readRequestLine(httpRequestLine);
		String method = httpRequestLine.getMethod();
		String uri = httpRequestLine.getUri();
		String protocol = httpRequestLine.getProtocol();

		if (method.length() < 1) {
			throw new ServletException("Missing HTTP Method");
		}

		if (uri.length() < 1) {
			throw new ServletException("Missing HTTP URI");
		}

		// 请求参数
		int question = uri.indexOf("?");
		if (question >= 0) {
			request.setUrlString(uri.substring(question + 1));
			uri = uri.substring(0, question);
		}

		// sessionId
		String sessionMatch = ";jsessionid=";
		int semicolon = uri.indexOf(sessionMatch);
		if (semicolon >= 0) {
			String rest = uri.substring(semicolon + sessionMatch.length());
			int semicolon2 = rest.indexOf("?");
			if (semicolon2 >= 0) {
				request.setSessionId(rest.substring(0, semicolon2));
				rest = rest.substring(semicolon2);
			} else {
				request.setSessionId(rest);
				rest = "";
			}
			uri = uri.substring(0, semicolon) + rest;
		}

		request.setRequestUrl(uri);
		request.setProtocol(protocol);
		request.setMethod(method);
	}

	// 解析请求头（）
	private void parseHeaders(SocketInputStream input) throws IOException {
		input.readHeader(request.getHeaders());
		String cookie = request.getHeaders().get("cookie");
		if (cookie == null) {
			cookie = request.getHeaders().get("COOKIE");
		}

		parseCookie(cookie);
	}

	// 解析cookie
	private void parseCookie(String header) {
		if (header == null || header.length() == 0) {
			request.setCookies(new ArrayList<>());
		} else {
			List<Map<String, String>> cookies = new ArrayList<>();
			while (header.length() > 0) {
				int semicolon = header.indexOf(";");
				if (semicolon < 0) {
					semicolon = header.length();
				}
				if (semicolon == 0) {
					break;
				}
				String token = header.substring(0, semicolon);
				if (semicolon < header.length()) {
					header = header.substring(semicolon + 1);
				} else {
					header = "";
				}

				int equals = token.indexOf("=");
				if (equals > 0) {
					HashMap<String, String> map = new HashMap<>(1);
					map.put(token.substring(0, equals).trim(), token.substring(equals + 1).trim());
					cookies.add(map);
				}

			}
		}
	}
}
