package softserve.netty.server.mapping.responce;

import io.netty.handler.mapping.PackageVersion;
import softserve.db.mapping.User;

public class UpdateUserResponsePackage extends PackageVersion {
	private User user;

	public UpdateUserResponsePackage(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
