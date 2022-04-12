package hu.szrnkapeter.minihttpserver.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.opentest4j.AssertionFailedError;

public class TestUtils {

	public static <S> void testPrivateConstructor(Class<S> singletonClass) throws SecurityException, NoSuchMethodException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		final Constructor<S> constructor = singletonClass.getDeclaredConstructor();
		constructor.setAccessible(true);
		constructor.newInstance();
		constructor.setAccessible(false);
	}
	
	public static void assertLogExists(List<LogRecord> logs, Level level, String expectedMessage) {
		Optional<LogRecord> foundEvent = logs.stream()
				.filter(log -> level == log.getLevel() && log.getMessage().contains(expectedMessage))
				.findAny();

		if (!foundEvent.isPresent()) {
			throw new AssertionFailedError("Log message('" + expectedMessage + "') cannot be found!");
		}
	}
	
	public static void assertLogNotExists(List<LogRecord> logs, Level level, String expectedMessage) {
		Optional<LogRecord> foundEvent = logs.stream()
				.filter(log -> level == log.getLevel() && log.getMessage().contains(expectedMessage))
				.findAny();

		if (foundEvent.isPresent()) {
			throw new AssertionFailedError("Log message('" + expectedMessage + "') cannot be found!");
		}
	}
}