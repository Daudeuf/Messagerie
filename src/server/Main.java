package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
	public static ArrayList<User> users;

	public static void main(String[] args) throws IOException {
		Main.users = new ArrayList<>();

		int port = 7890;

		ServerSocket s = new ServerSocket(port);

		while (true)
		{
			Socket so = s.accept();

			User u = new User(so);

			Main.users.add(u);

			ClientHandler h = new ClientHandler(so, u);
			Thread t = new Thread(h);
			t.start();
		}
	}
}
