package io.netty.handler.timeout.auth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutHandler;

public abstract class AuthenticationTimeoutHandler extends ReadTimeoutHandler {
	private AuthenticationProvider authoeizedChannel;

	public AuthenticationTimeoutHandler(int timeoutSeconds, AuthenticationProvider authoeizedChannel) {
		super(timeoutSeconds);
		if (authoeizedChannel == null) {
			throw new IllegalArgumentException("AuthoeizedChannel should not be null");
		}

		this.authoeizedChannel = authoeizedChannel;
	}

	protected abstract void channelAutzorizationTimeOut(ChannelHandlerContext ctx);

	@Override
	protected void readTimedOut(ChannelHandlerContext ctx) throws Exception {
		if (!authoeizedChannel.isAuthorized()) {
			channelAutzorizationTimeOut(ctx);
			super.readTimedOut(ctx);
		}
	}
}