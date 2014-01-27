package softserve.netty.server.beans;

import org.apache.log4j.Logger;

import softserve.netty.server.SocketChannelInitializer;
import softserve.netty.server.SocketServer;

public class ServerBean {

	private static final Logger LOG = Logger.getLogger(ServerBean.class);
	private static final SocketServer SOCKET_SERVER = new SocketServer();

	private int port = 9876;
	private int bossGroupThreadSize = 4;
	private int workGroupThreadSize = 4;

	public boolean startServer() {
		LOG.debug("ServerBean startServer");
		SOCKET_SERVER.init(port, bossGroupThreadSize, workGroupThreadSize, new SocketChannelInitializer());
		return SOCKET_SERVER.startServer();
	}

	public boolean stopServer() {
		LOG.debug("ServerBean stopServer");
		return SOCKET_SERVER.stopServer();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getBossGroupThreadSize() {
		return bossGroupThreadSize;
	}

	public void setBossGroupThreadSize(int bossGroupThreadSize) {
		this.bossGroupThreadSize = bossGroupThreadSize;
	}

	public int getWorkGroupThreadSize() {
		return workGroupThreadSize;
	}

	public void setWorkGroupThreadSize(int workGroupThreadSize) {
		this.workGroupThreadSize = workGroupThreadSize;
	}
}
