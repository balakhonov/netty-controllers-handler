package softserve.netty.server.handle.timeout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.auth.AuthoeizedChannel;
import io.netty.handler.timeout.auth.AuthorizationSocketTimeoutAdapter;

import org.apache.log4j.Logger;

public class SocketAuthorizationTimeoutListener extends AuthorizationSocketTimeoutAdapter {
	private static final Logger LOG = Logger.getLogger(SocketAuthorizationTimeoutListener.class);

	public SocketAuthorizationTimeoutListener(int timeoutSeconds, AuthoeizedChannel authoeizedChannel) {
		super(timeoutSeconds, authoeizedChannel);
	}

	@Override
	protected void channelAutzorizationTimeOut(ChannelHandlerContext ctx) {
		LOG.warn(ctx.channel());
	}

}