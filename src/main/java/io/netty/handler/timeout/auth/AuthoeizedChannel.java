package io.netty.handler.timeout.auth;

public interface AuthoeizedChannel {

	public boolean isAuthorized();

	public void setAuthorized(boolean authorized);
}
