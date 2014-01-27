package softserve.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.apache.log4j.Logger;

public class SocketServer implements Runnable {
	private static final Logger LOG = Logger.getLogger(SocketServer.class);

	private int port;
	private int bossGroupThreadSize;
	private int workGroupThreadSize;
	private ChannelHandler channelHandler;

	private boolean started = false;
	private boolean initialized = false;

	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	private Channel channel;
	private Thread workThread;

	/**
	 * 
	 * @param port
	 * @param bossGroupThreadSize
	 * @param workGroupThreadSize
	 */
	public synchronized void init(int port, int bossGroupThreadSize, int workGroupThreadSize,
			ChannelHandler channelHandler) {
		// TODO validate
		this.port = port;
		this.bossGroupThreadSize = bossGroupThreadSize;
		this.workGroupThreadSize = workGroupThreadSize;
		this.channelHandler = channelHandler;
		this.initialized = true;
	}

	/**
	 * 
	 * @return
	 */
	public synchronized boolean startServer() {
		if (!initialized) {
			LOG.warn("Socket server not initialized");
			return false;
		}

		if (started) {
			LOG.warn("Socket server already started at port " + this.port);
			return false;
		} else {
			LOG.info("Starting socket server");

			workThread = new Thread(this, "Socket server");
			workThread.start();
			started = true;
			return true;
		}
	}

	/**
	 * 
	 * @return
	 */
	public synchronized boolean stopServer() {
		if (!initialized) {
			LOG.warn("Socket server not initialized");
			return false;
		}

		if (started) {
			LOG.info("Stopping socket server");

			started = false;
			if (bossGroup != null && workerGroup != null) {
				bossGroup.shutdownGracefully();
				workerGroup.shutdownGracefully();
			}
			return true;
		} else {
			LOG.warn("Socket server already stopped");
			return false;
		}
	}

	public boolean isStarted() {
		return started;
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		if (!initialized) {
			LOG.warn("Socket server not initialized");
			return;
		}

		ServerBootstrap b = new ServerBootstrap();
		try {
			bossGroup = new NioEventLoopGroup(bossGroupThreadSize);
			workerGroup = new NioEventLoopGroup(workGroupThreadSize);

			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(channelHandler);
			// maximum length of the queue of incoming connections.
			b.option(ChannelOption.SO_BACKLOG, 128);
			//
			b.childOption(ChannelOption.SO_KEEPALIVE, false);

			LOG.info("Starting Socket Server at port " + port);
			channel = b.bind(port).sync().channel();
			channel.closeFuture().sync();
		} catch (InterruptedException e) {
			LOG.error(e);
		} finally {
			if (isStarted()) {
				stopServer();
			}
		}
	}
}
