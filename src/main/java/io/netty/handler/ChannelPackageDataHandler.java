package io.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.mapping.RequestPackageWrapper;
import io.netty.handler.timeout.auth.AuthoeizedChannel;

import org.apache.log4j.Logger;

public class ChannelPackageDataHandler extends ChannelInboundHandlerAdapter implements AuthoeizedChannel {
	private static final Logger LOG = Logger.getLogger(ChannelPackageDataHandler.class);

	private boolean authorized = false;


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		LOG.trace("channelUnregistered");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		LOG.trace("channelInactive");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object request) throws Exception {
		LOG.debug("Recieved data from client:" + request);
		if (request instanceof RequestPackageWrapper) {
			ChannelJsonPackageProcessor.process((RequestPackageWrapper) request,this);
		} else {
			throw new IllegalStateException("Unknown package: " + request);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		LOG.trace("channelReadComplete flush");
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOG.warn("Unexpected exception from downstream.", cause);
		ctx.close();
	}

	@Override
	public boolean isAuthorized() {
		return authorized;
	}

	@Override
	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

}