package test;

import server.ex02.Constants;
import test.load.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class TestURLLoader {

	public static void main(String[] args) {
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
			myClass = loader.loadClass("test.load.Test");
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}

		Test test = null;
		try {
			test = (Test) myClass.newInstance();
			test.test();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
