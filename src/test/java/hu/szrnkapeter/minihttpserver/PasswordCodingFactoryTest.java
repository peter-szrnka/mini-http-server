package hu.szrnkapeter.minihttpserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PasswordCodingFactoryTest {

	@Test
	void test_base64() {
		final PasswordCodingFactory factory = new PasswordCodingFactory("base64");

		final String encodedString = factory.getManager().encode("password");
		assertEquals("cGFzc3dvcmQ=", encodedString);

		final String decodedString = factory.getManager().decode(encodedString);
		assertEquals( "password", decodedString);
	}

	@Test
	void test_unknown() {
		UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> new PasswordCodingFactory("base85"));
		assertEquals("Currently only Base64 password decoding is supported!", exception.getMessage());
	}
}
