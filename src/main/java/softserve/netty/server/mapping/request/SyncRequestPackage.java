package softserve.netty.server.mapping.request;

import io.netty.handler.mapping.PackageVersion;

public class SyncRequestPackage extends PackageVersion {
	private String tooken;

	public SyncRequestPackage(String tooken) {
		this.tooken = tooken;
	}

	public String getTooken() {
		return tooken;
	}

	public void setTooken(String tooken) {
		this.tooken = tooken;
	}

}
