package io.netty.handler.timeout.auth;

public interface AuthenticationProvider {

	public boolean isAuthorized();

	public void setAuthorized(boolean authorized);
}
