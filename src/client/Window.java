package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Window extends JFrame implements ActionListener {
	private JTextArea   txtArea;
	private JTextField  txtFldInput;
	private PrintWriter out;

	public Window( Socket srv ) throws IOException {
		this.out = new PrintWriter(srv.getOutputStream(), true);

		this.setLocation(20, 20);
		this.setSize(425, 300);
		this.setLayout( new BorderLayout(20, 20));

		JPanel panelTxt   = new JPanel();
		JPanel panelInput = new JPanel();

		this.add( panelTxt,   BorderLayout.CENTER );
		this.add( panelInput, BorderLayout.SOUTH  );

		this.txtArea     = new JTextArea();
		this.txtFldInput = new JTextField(32);

		panelTxt  .add( this.txtArea     );
		panelInput.add( this.txtFldInput );

		this.txtFldInput.addActionListener( this );

		this.txtArea.setEnabled( false );
		this.txtArea.setOpaque( false );

		panelTxt.setBackground( Color.DARK_GRAY );

		this.setDefaultCloseOperation( EXIT_ON_CLOSE );
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String line = this.txtFldInput.getText();

		this.out.println(line);
		this.txtFldInput.setText("");
	}

	public void addTextLine( String line )
	{
		/*String s = String.format(
				"%s\n%s",
				this.txtArea.getText(),
				line
		);*/

		this.txtArea.append(line + '\n');
	}
}
