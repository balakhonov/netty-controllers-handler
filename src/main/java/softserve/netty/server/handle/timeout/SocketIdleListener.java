package softserve.netty.server.handle.timeout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.SocketIdleStateAdapter;

import org.apache.log4j.Logger;

public class SocketIdleListener extends SocketIdleStateAdapter {
	private static final Logger LOG = Logger.getLogger(SocketIdleListener.class);

	public SocketIdleListener(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
		super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
	}

	@Override
	public void channelFirstReadTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt) {
		LOG.trace(ctx);
	}

	@Override
	public void channelReadTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt) {
		LOG.trace(ctx);
		ctx.close();
	}

	@Override
	public void channelFirstWriteTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt) {
		LOG.trace(ctx);
	}

	@Override
	public void channelWriteTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt) {
		LOG.trace(ctx);
		ctx.close();
	}

	@Override
	public void channelFirstAllTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt) {
		LOG.trace(ctx);
	}

	@Override
	public void channelAllTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt) {
		LOG.trace(ctx);
		ctx.close();
	}

}
