package softserve.netty.client;

import com.google.gson.Gson;

public class Test {

	public static void main(String[] asd) {
		A a = new A();
		a.setCommand("comm");
		a.setVersion("1.1");
		a.setData("data");

		String ps = new Gson().toJson(a);
		System.out.println("ps:" + ps);

		Package p = new Gson().fromJson(ps, A.class);

		System.out.println("p:" + p.toString());
	}

	static abstract class Package {
		private String command;
		private Object data;

		public String getCommand() {
			return command;
		}

		public void setCommand(String command) {
			this.command = command;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return getCommand() + " | " + getData();
		}
	}


	static class A extends Package {
		private String version;

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		@Override
		public String toString() {
			return getCommand() + " | " + getVersion() + " | " + getData();
		}
	}

	static class B extends Package {
		private String version;
		private String a;
		private String b;

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return getCommand() + " | " + getVersion() + " | " + getData() + " | " + getA() + " | " + getB();
		}
	}
}
