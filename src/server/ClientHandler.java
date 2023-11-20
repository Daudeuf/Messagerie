package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private Socket s;
	private User   user;

	public ClientHandler(Socket s, User user)
	{
		this.s    = s;
		this.user = user;
	}

	@Override
	public void run() {
		try {
			System.out.printf("Client connecté : %s\n", this.s.getInetAddress());

			while (!this.user.isClosed())
			{
				String str  = user.receiveLine();
				String line = String.format("%s > %s", this.user.getName(), str);

				if (!str.startsWith("/"))
				{
					System.out.println(line);

					for (User u : Main.users) {
						if (!u.isClosed()) {
							u.sendLine(line);
						}
					}
				}
				else
				{
					String   rawCommand = str.substring(1);
					String[] args = rawCommand.split(" ");
					String   command = args[0];

					System.out.printf("%s  use command  %s\n", this.user.getName(), command);

					if (command.equals("name") && args.length >= 2)
					{
						System.out.printf("%s à changer son nom en %s\n", this.user.getName(), args[1]);
						this.user.setName(args[1]);
					}
				}
			}

			System.out.printf("Client déconnecté : %s\n", this.s.getInetAddress());
		} catch (IOException ignored) {}
	}
}
