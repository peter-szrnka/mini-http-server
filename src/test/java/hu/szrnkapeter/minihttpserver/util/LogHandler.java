package hu.szrnkapeter.minihttpserver.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LogHandler extends Handler {
	
	private List<LogRecord> list = new ArrayList<>();

	@Override
	public void publish(LogRecord record) {
		list.add(record);
	}

	@Override
	public void flush() {
	}

	@Override
	public void close() throws SecurityException {
	}
	
	public List<LogRecord> getList() {
		return list;
	}

	public void clearRecords() {
		list.clear();
	}
}