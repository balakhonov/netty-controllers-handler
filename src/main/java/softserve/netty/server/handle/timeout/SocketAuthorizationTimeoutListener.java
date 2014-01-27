package softserve.netty.server.handle.timeout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.auth.AuthenticationProvider;
import io.netty.handler.timeout.auth.AuthenticationTimeoutHandler;

import org.apache.log4j.Logger;

public class SocketAuthorizationTimeoutListener extends AuthenticationTimeoutHandler {
	private static final Logger LOG = Logger.getLogger(SocketAuthorizationTimeoutListener.class);

	public SocketAuthorizationTimeoutListener(int timeoutSeconds, AuthenticationProvider authoeizedChannel) {
		super(timeoutSeconds, authoeizedChannel);
	}

	@Override
	protected void channelAutzorizationTimeOut(ChannelHandlerContext ctx) {
		LOG.warn(ctx.channel());
	}

}