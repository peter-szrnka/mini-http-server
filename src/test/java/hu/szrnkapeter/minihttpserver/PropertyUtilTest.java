package hu.szrnkapeter.minihttpserver;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.szrnkapeter.minihttpserver.util.LogHandler;
import hu.szrnkapeter.minihttpserver.util.TestUtils;

class PropertyUtilTest {
	
	private LogHandler logHandler = new LogHandler();
	private static final Logger LOGGER = Logger.getLogger(PropertyUtil.class.getName());
	
	@BeforeEach
	void setup() {
		LOGGER.addHandler(logHandler);
	}
	
	@AfterEach
	void teardown() {
		logHandler.clearRecords();
		LOGGER.removeHandler(logHandler);
	}
	
	@Test
	void testPrivateConstructor() {
		assertDoesNotThrow(() -> TestUtils.testPrivateConstructor(PropertyUtil.class));
	}

	@Test
	void test_withoutPropertyFile() {
		final Config config = PropertyUtil.loadProperties("test.properties");
		assertEquals("http", config.getServerType());
	}
	
	@Test
	void test_withBadPropertyFile() {
		PropertyUtil.loadProperties("config-bad.properties");
		
		TestUtils.assertLogExists(logHandler.getList(), Level.WARNING, "Currently only Base64 password decoding is supported!");
	}

	@Test
	void test_withPropertyFile() {
		final Config config = PropertyUtil.loadProperties();

		assertEquals("http", config.getServerType());
	}
}