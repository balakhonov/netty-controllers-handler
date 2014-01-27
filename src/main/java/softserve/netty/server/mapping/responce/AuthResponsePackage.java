package softserve.netty.server.mapping.responce;

import io.netty.handler.mapping.PackageVersion;
import softserve.db.mapping.User;

public class AuthResponsePackage extends PackageVersion {
	private String tooken;
	private User user;

	public AuthResponsePackage(String tooken, User user) {
		this.tooken = tooken;
		this.user = user;
	}

	public String getTooken() {
		return tooken;
	}

	public void setTooken(String tooken) {
		this.tooken = tooken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Tooken: " + tooken + ", user: " + user;
	}

}
