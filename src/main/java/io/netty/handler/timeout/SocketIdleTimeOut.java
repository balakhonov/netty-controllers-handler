package io.netty.handler.timeout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;

public interface SocketIdleTimeOut {

	public void channelFirstReadTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt);

	public void channelReadTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt);

	public void channelFirstWriteTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt);

	public void channelWriteTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt);

	public void channelFirstAllTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt);

	public void channelAllTimeOut(ChannelHandlerContext ctx, IdleStateEvent evt);
}
