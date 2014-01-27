package io.netty.handler.mapping;

public class ResponsePackage {

	private int resultCode;
	private String message;

	public ResponsePackage() {
		// no code
	}

	public ResponsePackage(int resultCode) {
		this.resultCode = resultCode;
	}

	public ResponsePackage(int resultCode, String message) {
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
