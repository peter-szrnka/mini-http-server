package hu.szrnkapeter.minihttpserver;

public class Main {

	public static void main(final String[] args) throws Exception {
		final Config config = PropertyUtil.loadProperties();
		final WebServer server = new WebServer(config);
		server.start(true);
	}
}