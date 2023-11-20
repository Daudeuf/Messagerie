package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class User
{
	private static int ids = 0;

	private BufferedReader i;
	private PrintWriter    o;
	private Socket         s;
	private int            id;
	private String         name;

	public User(Socket s) throws IOException
	{
		this.s = s;

		this.id   = ++User.ids;
		this.name = String.format("User %02d", this.id);

		this.i = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.o = new PrintWriter(s.getOutputStream(), true);
	}

	public void sendLine( String line )
	{
		this.o.println(line);
	}

	public String receiveLine() throws IOException {
		return this.i.readLine();
	}

	public boolean isClosed()
	{
		return this.s.isClosed();
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = String.format("%s [%02d]", name, this.id);
	}
}
