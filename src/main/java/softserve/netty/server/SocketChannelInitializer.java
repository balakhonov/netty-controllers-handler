package softserve.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ChannelPackageDataHandler;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.json.ChannelPackageDecoder;
import io.netty.handler.codec.json.ChannelPackageEncoder;

import org.apache.log4j.Logger;

import softserve.netty.server.handle.timeout.SocketAuthorizationTimeoutListener;
import softserve.netty.server.handle.timeout.SocketIdleListener;

public class SocketChannelInitializer extends ChannelInitializer<SocketChannel> {
	private static final Logger LOG = Logger.getLogger(SocketChannelInitializer.class);

	/**
	 * 
	 */
	private int frameSize = Integer.MAX_VALUE;

	/**
	 * 
	 */
	private int authorizationTimeOut = 1;

	/**
	 * 
	 */
	private int readTimeOut = 4; // 10 * 60;// 10 * 60;

	/**
	 * 
	 */
	private int writeTimeOut = 4; // 10 * 60;

	/**
	 * 
	 */
	private int allTImeOut = 0;

	/**
	 * 
	 */

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		LOG.debug("Init server channel: " + ch);
		ChannelPackageDataHandler serverHandler = new ChannelPackageDataHandler();

		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(frameSize, Delimiters.lineDelimiter()));
		pipeline.addLast("decoder", new ChannelPackageDecoder());
		pipeline.addLast("encoder", new ChannelPackageEncoder());
		pipeline.addLast("handler", serverHandler);
		pipeline.addLast("idleStateHandler", new SocketIdleListener(readTimeOut, writeTimeOut, allTImeOut));
		pipeline.addLast("authTimeOutHandler", new SocketAuthorizationTimeoutListener(authorizationTimeOut,
				serverHandler));
	}
}
