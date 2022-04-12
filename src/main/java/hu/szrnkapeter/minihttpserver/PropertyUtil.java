package hu.szrnkapeter.minihttpserver;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import hu.szrnkapeter.minihttpserver.PasswordCodingFactory.PassWordManager;

public class PropertyUtil {
	
	private static final Logger LOGGER = Logger.getLogger(PropertyUtil.class.getName());
	
	private PropertyUtil() {}

	public static Config loadProperties() {
		return loadProperties("config.properties");
	}

	public static Config loadProperties(final String propertyFile) {
		final Config config = new Config();
		final Properties prop = new Properties();

		if (PropertyUtil.class.getClassLoader().getResourceAsStream(propertyFile) == null) {
			LOGGER.info(() -> "No configuration file exists. Server starts in HTTP mode.");
			config.setServerPort(8080);
			config.setServerType("http");
			config.setWwwDir(".");
			return config;
		}

		try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream(propertyFile)) {
			prop.load(input);
			final PassWordManager passManager = new PasswordCodingFactory(prop.getProperty("password.encrypttype")).getManager();

			config.setEncriptType(prop.getProperty("password.encrypttype"));
			config.setKeystoreLocation(prop.getProperty("keystore.location"));
			config.setKeystorePassword(passManager.decode(prop.getProperty("keystore.password")));
			config.setServerPort(Integer.valueOf(prop.getProperty("server.port")));
			config.setServerType(prop.getProperty("server.type"));
			config.setWwwDir(prop.getProperty("www.dir"));
			config.setTruststoreLocation(prop.getProperty("truststore.location"));
			config.setTruststorePassword(passManager.decode(prop.getProperty("truststore.password")));

		} catch (final Exception ex) {
			LOGGER.warning(ex::getMessage);
		}

		return config;
	}
}
