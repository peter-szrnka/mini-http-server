package hu.szrnkapeter.minihttpserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import hu.szrnkapeter.minihttpserver.util.LogHandler;
import hu.szrnkapeter.minihttpserver.util.TestUtils;

class WebServerTest {
	
	private LogHandler logHandler = new LogHandler();
	private static final Logger LOGGER = Logger.getLogger(WebServer.class.getName());
	private static WebServer server;
	
	@BeforeEach
	void setup() {
		LOGGER.addHandler(logHandler);
		
		Config config = PropertyUtil.loadProperties();
		server = new WebServer(config);
	}
	
	@AfterEach
	void teardown() {
		logHandler.clearRecords();
		LOGGER.removeHandler(logHandler);
	}

	@Test
	void testWithoutConfig() {
		server = new WebServer(new Config());
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> server.start(true));
		assertEquals(null, exception.getMessage());
	}
	
	@Test
	void testWithHttpConfig() throws Exception {
		// act
		server.start(false);
		server.stop();
		
		// assert
		TestUtils.assertLogExists(logHandler.getList(), Level.INFO, "WebServer started on http port 8081");
		TestUtils.assertLogExists(logHandler.getList(), Level.INFO, "WebServer stopped");
	}
	
	@Test
	@Disabled
	void testWithHttpsConfig() throws Exception {
		generateKeystore();
		
		Config config = PropertyUtil.loadProperties("config-https.properties");
		server = new WebServer(config);
		
		// act
		server.start(false);
		server.stop();
		
		// assert
		TestUtils.assertLogExists(logHandler.getList(), Level.INFO, "WebServer started on https port 8081");
		TestUtils.assertLogExists(logHandler.getList(), Level.INFO, "WebServer stopped");
	}
	
	private static void generateKeystore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		
		//System.out.println("Value = " + new String(Base64.getEncoder().encode("P4ssword".getBytes())));

		char[] password = "P4ssword".toCharArray();
		ks.load(null, password);

		Certificate cert = null;
		ks.setCertificateEntry("localhost", cert);

		// Store away the keystore.
		FileOutputStream fos = new FileOutputStream("test_keystore.jks");
		ks.store(fos, password);
		fos.close();
		
	}

	@Test
	void testStop() throws Exception {
		server.stop();
		TestUtils.assertLogNotExists(logHandler.getList(), Level.INFO, "WebServer stopped");
	}
}