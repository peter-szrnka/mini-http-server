package hu.szrnkapeter.minihttpserver;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Password encoder factory
 */
public class PasswordCodingFactory {

	/**
	 * Concrete implementation of PasswordManager
	 */
	static class Base64PasswordManager implements PassWordManager {

		/*
		 * (non-Javadoc)
		 * @see hu.szrnkapeter.minihttpserver.PasswordCodingFactory.PassWordManager#decode(java.lang.String)
		 */
		@Override
		public String decode(final String input) {
			return new String(Base64.getDecoder().decode(input), StandardCharsets.UTF_8);
		}

		/*
		 * (non-Javadoc)
		 * @see hu.szrnkapeter.minihttpserver.PasswordCodingFactory.PassWordManager#encode(java.lang.String)
		 */
		@Override
		public String encode(final String input) {
			return Base64.getEncoder().encodeToString(input.getBytes());
		}

	}

	
	static interface PassWordManager {
		
		/**
		 * Decodes a string
		 * 
		 * @param input Input encoded credential
		 * @return Decoded password
		 */
		String decode(String input);

		/**
		 * Encodes a string
		 * 
		 * @param input Input credential
		 * @return Encoded password
		 */
		String encode(String input);
	}

	private PassWordManager manager;

	public PasswordCodingFactory(final String method) {
		if ("base64".equals(method)) {
			manager = new Base64PasswordManager();
		} else {
			throw new UnsupportedOperationException("Currently only Base64 password decoding is supported!");
		}
	}

	public PassWordManager getManager() {
		return manager;
	}
}
