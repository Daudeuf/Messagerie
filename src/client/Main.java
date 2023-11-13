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

		System.out.printf("IP : ");
		String hostName = scanner.nextLine();

		System.out.printf("Port : ");
		int portNumber = scanner.nextInt();

		Socket srv = new Socket(hostName, portNumber);

		PrintWriter    out = new PrintWriter(srv.getOutputStream(), true);

		new Thread(() -> {
			try {
				BufferedReader in  = new BufferedReader(new InputStreamReader(srv.getInputStream()));

				String str = in.readLine();

				System.out.println(str);

				in.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).start();

		while (srv.isConnected())
		{
			String str = scanner.nextLine();
			out.println(str);
		}

		out.close();
		scanner.close();
	}
}
