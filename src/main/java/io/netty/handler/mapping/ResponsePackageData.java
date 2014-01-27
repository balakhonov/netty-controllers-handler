package io.netty.handler.mapping;

public class ResponsePackageData {

	private int resultCode;
	private String message;

	public ResponsePackageData() {
		// no code
	}

	public ResponsePackageData(int resultCode) {
		this.resultCode = resultCode;
	}

	public ResponsePackageData(int resultCode, String message) {
		this.resultCode = resultCode;
		this.message = message;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(", resultCode: " + resultCode);
		sb.append(", message: " + message);
		return sb.toString();
	}
}
