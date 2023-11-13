package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		ArrayList<Socket> users = new ArrayList<>();
		int port = 7890;

		ServerSocket s = new ServerSocket(port);

		while (true)
		{
			Socket so = s.accept();
			users.add(so);
			ClientHandler h = new ClientHandler(so, users);
			Thread t = new Thread(h);
			t.start();
		}
	}
}
