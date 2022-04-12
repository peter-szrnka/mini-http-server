package hu.szrnkapeter.minihttpserver;

public class Config {

	private String encriptType;
	private String keystoreLocation;
	private String keystorePassword;
	private int serverPort;
	private String serverType;
	private String truststoreLocation;
	private String truststorePassword;
	private String wwwDir;

	public String getEncriptType() {
		return encriptType;
	}

	public String getKeystoreLocation() {
		return keystoreLocation;
	}

	public String getKeystorePassword() {
		return keystorePassword;
	}

	public int getServerPort() {
		return serverPort;
	}

	public String getServerType() {
		return serverType;
	}

	public String getTruststoreLocation() {
		return truststoreLocation;
	}

	public String getTruststorePassword() {
		return truststorePassword;
	}

	public String getWwwDir() {
		return wwwDir;
	}

	public void setEncriptType(final String encriptType) {
		this.encriptType = encriptType;
	}

	public void setKeystoreLocation(final String keystoreLocation) {
		this.keystoreLocation = keystoreLocation;
	}

	public void setKeystorePassword(final String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}

	public void setServerPort(final int serverPort) {
		this.serverPort = serverPort;
	}

	public void setServerType(final String serverType) {
		this.serverType = serverType;
	}

	public void setTruststoreLocation(final String truststoreLocation) {
		this.truststoreLocation = truststoreLocation;
	}

	public void setTruststorePassword(final String truststorePassword) {
		this.truststorePassword = truststorePassword;
	}

	public void setWwwDir(final String wwwDir) {
		this.wwwDir = wwwDir;
	}
}