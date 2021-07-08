package server.ex02;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor {
	public void process(Request request, Response response) {
		String uri = request.getUri();
		String servletName = String.format("%s.%s", Constants.WEB_ROOT, uri.substring(uri.lastIndexOf("/") + 1));
		URLClassLoader loader = null;
		try {
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			String responsitory = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
			urls[0] = new URL(null, responsitory, streamHandler);
			loader = new URLClassLoader(urls);
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		Class myClass = null;
		try {
			myClass = loader.loadClass(servletName);
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}

		Servlet servlet = null;

		try {
			servlet = (Servlet) myClass.newInstance();
			servlet.service(request, response);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
