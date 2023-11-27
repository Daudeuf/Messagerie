package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);

		System.out.print("IP : ");
		String hostName = scanner.nextLine();

		System.out.print("Port : ");
		int portNumber = scanner.nextInt();

		scanner.close();

		Socket srv = new Socket(hostName, portNumber);
		Window w   = new Window( srv );

		new Thread(() -> {
			try
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(srv.getInputStream()));

				while (!srv.isClosed()) {
					String str = in.readLine();

					System.out.println(str);
					Thread.sleep(500);

					w.addTextLine(str);
				}

				System.out.println("closed !");

				in.close();
			}
			catch (Exception ignored) {}
		}).start();

		w.setVisible( true );
	}
}
