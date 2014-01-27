package softserve.netty.controller;

import io.netty.annotation.Command;
import io.netty.annotation.SocketController;
import io.netty.handler.mapping.ResponsePackage;
import io.netty.handler.mapping.ResponsePackageWrapper;
import io.netty.handler.timeout.auth.AuthoeizedChannel;
import softserve.db.mapping.User;
import softserve.netty.server.mapping.request.AuthRequestPackage;
import softserve.netty.server.mapping.request.SyncRequestPackage;
import softserve.netty.server.mapping.request.UpdateUserRequestPackage;
import softserve.netty.server.mapping.responce.AuthResponsePackage;
import softserve.netty.server.mapping.responce.UpdateUserResponsePackage;

@SocketController
public class ChannelPackageController {
	private static final int OK = 0;
	private static final int OTHER = 999;

	private static final String TEST_EMAIL = "balakhonov.yuriy@gmail.com";
	private static final String TEST_PASS = "123456";
	private static final String TEST_TOOKEN = "123";
	private static final User TEST_USER = new User(1, "Yurii", 23);

	private ResponsePackage successAuth(AuthoeizedChannel ac) {
		ac.setAuthorized(true);
		return new ResponsePackageWrapper<AuthResponsePackage>(OK, new AuthResponsePackage(TEST_TOOKEN, TEST_USER));
	}

	@Command(command = "/ping")
	public ResponsePackage ping() {
		return new ResponsePackage(OK, "pong");
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	@Command(command = "/auth")
	public ResponsePackage auth(AuthRequestPackage request, AuthoeizedChannel ac) {
		String email = request.getEmail();
		String password = request.getPassword();

		if ((email == null || password == null) || !email.trim().equals(TEST_EMAIL)
				|| !password.trim().equals(TEST_PASS)) {
			return new ResponsePackage(OTHER, "User not found");
		} else {
			return successAuth(ac);
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@Command(command = "/sync")
	public ResponsePackage sync(SyncRequestPackage request, AuthoeizedChannel ac) {
		String tooken = request.getTooken();

		if (tooken == null || !tooken.trim().equals(TEST_TOOKEN)) {
			return new ResponsePackage(OTHER, "Not authorized");
		} else {
			return successAuth(ac);
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@Command(command = "/user/update")
	public ResponsePackage updateUser(UpdateUserRequestPackage request, AuthoeizedChannel ac) {
		if (ac.isAuthorized()) {
			TEST_USER.setAge(24);
			TEST_USER.setName("Dmitrii");

			return new ResponsePackageWrapper<UpdateUserResponsePackage>(OK, new UpdateUserResponsePackage(TEST_USER));
		} else {
			return new ResponsePackage(OTHER, "Not authorized");
		}
	}
}
