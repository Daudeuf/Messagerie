package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
	private Socket s;
	private ArrayList<Socket> users;

	public ClientHandler(Socket s, ArrayList<Socket> users)
	{
		this.s     = s;
		this.users = users;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));

			System.out.printf("Client connecté : %s\n", this.s.getInetAddress());

			while (this.s.isConnected())
			{
				String str = in.readLine();

				System.out.printf("%s -> %s\n", this.s.getInetAddress(), str);

				for (Socket s : this.users) {
					if (s.isConnected())
					{
						PrintWriter out = new PrintWriter(s.getOutputStream(), true);

						out.println(str);
						out.close();
					}
				}
			}

			in.close();

			System.out.printf("Client déconnecté : %s\n", this.s.getInetAddress());
		} catch (IOException ignored) {}
	}
}
