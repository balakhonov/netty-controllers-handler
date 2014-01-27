package io.netty.handler.timeout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

public abstract class SocketIdleStateAdapter extends IdleStateHandler implements SocketIdleTimeOutListener {

	public SocketIdleStateAdapter(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
		super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
	}

	@Override
	protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
		if (evt.isFirst()) {
			if (evt.state() == IdleState.READER_IDLE) {
				channelFirstReadTimeOut(ctx, evt);
			} else if (evt.state() == IdleState.WRITER_IDLE) {
				channelFirstWriteTimeOut(ctx, evt);
			} else if (evt.state() == IdleState.ALL_IDLE) {
				channelFirstAllTimeOut(ctx, evt);
			}
		} else {
			if (evt.state() == IdleState.READER_IDLE) {
				channelReadTimeOut(ctx, evt);
			} else if (evt.state() == IdleState.WRITER_IDLE) {
				channelWriteTimeOut(ctx, evt);
			} else if (evt.state() == IdleState.ALL_IDLE) {
				channelAllTimeOut(ctx, evt);
			}
		}
	}
}
