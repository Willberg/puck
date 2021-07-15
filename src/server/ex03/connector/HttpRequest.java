package server.ex03.connector;

import server.ex03.util.Enumerator;
import server.ex03.util.ParameterMap;
import server.ex03.util.RequestUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

public class HttpRequest implements HttpServletRequest {
	private InputStream input;
	private String urlString;
	private String sessionId;
	private String requestUrl;
	private String protocol;
	private String method;
	protected boolean parsed = false;
	protected HashMap<String, String> headers = new HashMap<>();
	protected ArrayList<HashMap<String, String>> cookies = new ArrayList<>();
	protected ParameterMap parameters = null;

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setCookies(ArrayList<HashMap<String, String>> cookies) {
		this.cookies = cookies;
	}

	public HttpRequest(InputStream input) {
		this.input = input;
	}

	@Override
	public String getAuthType() {
		return null;
	}

	@Override
	public Cookie[] getCookies() {
		return cookies.toArray(new Cookie[cookies.size()]);
	}

	@Override
	public long getDateHeader(String s) {
		return 0;
	}

	@Override
	public String getHeader(String s) {
		return null;
	}

	@Override
	public Enumeration<String> getHeaders(String s) {
		return null;
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		return null;
	}

	@Override
	public int getIntHeader(String s) {
		return 0;
	}

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public String getPathInfo() {
		return null;
	}

	@Override
	public String getPathTranslated() {
		return null;
	}

	@Override
	public String getContextPath() {
		return null;
	}

	@Override
	public String getQueryString() {
		return urlString;
	}

	@Override
	public String getRemoteUser() {
		return null;
	}

	@Override
	public boolean isUserInRole(String s) {
		return false;
	}

	@Override
	public Principal getUserPrincipal() {
		return null;
	}

	@Override
	public String getRequestedSessionId() {
		return null;
	}

	@Override
	public String getRequestURI() {
		return this.requestUrl;
	}

	@Override
	public StringBuffer getRequestURL() {
		return null;
	}

	@Override
	public String getServletPath() {
		return null;
	}

	@Override
	public HttpSession getSession(boolean b) {
		return null;
	}

	@Override
	public HttpSession getSession() {
		return null;
	}

	@Override
	public String changeSessionId() {
		return null;
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		return false;
	}

	@Override
	public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
		return false;
	}

	@Override
	public void login(String s, String s1) throws ServletException {

	}

	@Override
	public void logout() throws ServletException {

	}

	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		return null;
	}

	@Override
	public Part getPart(String s) throws IOException, ServletException {
		return null;
	}

	@Override
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
		return null;
	}

	@Override
	public Object getAttribute(String s) {
		return null;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

	}

	@Override
	public int getContentLength() {
		return 0;
	}

	@Override
	public long getContentLengthLong() {
		return 0;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return null;
	}

	@Override
	public String getParameter(String name) {
		try {
			parseParameters();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String values[] = (String[]) parameters.get(name);
		if (values != null)
			return (values[0]);
		else
			return (null);
	}

	@Override
	public Enumeration<String> getParameterNames() {
		try {
			parseParameters();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (new Enumerator(parameters.keySet()));
	}

	@Override
	public String[] getParameterValues(String name) {
		try {
			parseParameters();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String values[] = (String[]) parameters.get(name);
		if (values != null)
			return (values);
		else
			return (null);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		try {
			parseParameters();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.parameters;
	}

	@Override
	public String getProtocol() {
		return null;
	}

	@Override
	public String getScheme() {
		return null;
	}

	@Override
	public String getServerName() {
		return null;
	}

	@Override
	public int getServerPort() {
		return 0;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return null;
	}

	@Override
	public String getRemoteAddr() {
		return null;
	}

	@Override
	public String getRemoteHost() {
		return null;
	}

	@Override
	public void setAttribute(String s, Object o) {

	}

	@Override
	public void removeAttribute(String s) {

	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public Enumeration<Locale> getLocales() {
		return null;
	}

	@Override
	public boolean isSecure() {
		return false;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String s) {
		return null;
	}

	@Override
	public String getRealPath(String s) {
		return null;
	}

	@Override
	public int getRemotePort() {
		return 0;
	}

	@Override
	public String getLocalName() {
		return null;
	}

	@Override
	public String getLocalAddr() {
		return null;
	}

	@Override
	public int getLocalPort() {
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		return null;
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		return null;
	}

	@Override
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
		return null;
	}

	@Override
	public boolean isAsyncStarted() {
		return false;
	}

	@Override
	public boolean isAsyncSupported() {
		return false;
	}

	@Override
	public AsyncContext getAsyncContext() {
		return null;
	}

	@Override
	public DispatcherType getDispatcherType() {
		return null;
	}

	protected void parseParameters() throws IOException {
		if (parsed) {
			return;
		}

		ParameterMap result = parameters;
		if (result == null) {
			result = new ParameterMap();
		}
		result.setLocked(false);

		String encoding = "ISO-8859-1";

		String queryString = getQueryString();
		try {
			RequestUtil.parseParameters(result, queryString, encoding);
		} catch (Throwable t) {

		}

		if ("POST".equalsIgnoreCase(getMethod())) {
			StringBuilder sb = new StringBuilder();
			while (true) {
				int ch = input.read();
				if (ch < 0) {
					break;
				}
				sb.append(ch);
			}

			RequestUtil.parseParameters(result, sb.toString(), encoding);
			input.close();
		}

		result.setLocked(true);
		parsed = true;
		parameters = result;
	}


}
