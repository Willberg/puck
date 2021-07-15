package server.ex03.connector;

import server.ex03.util.StringManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class SocketInputStream extends InputStream {
	/**
	 * Underlying input stream.
	 */
	protected InputStream is;

	protected static StringManager sm = StringManager.getManager(Constants.Package);

	@Override
	public int read() throws IOException {
		return 0;
	}

	public SocketInputStream(InputStream is) {
		this.is = is;
	}

	public void readRequestLine(HttpRequestLine httpRequestLine) throws IOException {
		String requestLine = readLine();

		if (requestLine != null) {
			int idx1 = requestLine.indexOf(" ");
			int idx2 = requestLine.lastIndexOf(" ");
			if (idx1 > 0) {
				httpRequestLine.setMethod(requestLine.substring(0, idx1));
				if (idx2 > idx1) {
					httpRequestLine.setUri(requestLine.substring(idx1 + 1, idx2));
					httpRequestLine.setProtocol(requestLine.substring(idx2 + 1));
				}
			}
		}

	}

	private String readLine() throws IOException {
		StringBuilder sb = new StringBuilder();
		while (true) {
			int ch = is.read();
			if (ch < 0) {
				break;
			} else if (ch == '\r') {
				continue;
			} else if (ch == '\n') {
				break;
			}
			sb.append((char) ch);
		}

		if (sb.length() > 0) {
			return sb.toString();
		} else {
			return null;
		}
	}

	public void readHeader(HashMap<String, String> headers) throws IOException {
		String line = readLine();
		while (line != null) {
			if (line.length() > 0) {
				int idx = line.indexOf(":");
				if (idx > 0) {
					String name = line.substring(0, idx);
					String value = line.substring(idx + 1);
					headers.put(name, value.trim());
				}
			}
			line = readLine();
		}
	}
}
