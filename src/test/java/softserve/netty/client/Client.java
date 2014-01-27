package softserve.netty.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static int counter = 0;

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket client = new Socket("localhost", 9878);

		PrintWriter pw = new PrintWriter(client.getOutputStream());
		while (++counter < 1) {
			pw.write("{command:\"command\",data:{field:12}}\n\r");
			pw.flush();
			System.out.println(counter);
		}

		Thread.sleep(5000);
		System.out.println(client.isBound() + " " + client.isConnected() + " " + client.isInputShutdown() + " "
				+ client.isOutputShutdown() + " " + client.isClosed());

		client.close();
	}
}
