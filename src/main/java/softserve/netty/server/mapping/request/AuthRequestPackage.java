package softserve.netty.server.mapping.request;

import io.netty.handler.mapping.PackageVersion;

public class AuthRequestPackage  extends PackageVersion {
	private String email;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
