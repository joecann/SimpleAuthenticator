package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class MyFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton loginButton,cancelButton;
	private JTextField emailAddress;
	private JPasswordField pw;
	private JLabel infolabel;
	
	public MyFrame() {
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(400,400));
		panel.setLayout(new MigLayout("wrap 2, insets 100 20 20 20"));
					
		JLabel emailText = new JLabel();
		emailText.setText("Username");
		emailText.setFont(new Font(null,Font.BOLD, 12));
		emailText.setForeground(Color.black);
		panel.add(emailText);		
		
		emailAddress = new JTextField();
		emailAddress.setColumns(40);
		emailAddress.setFont(new Font(null,Font.PLAIN,15));
		panel.add(emailAddress, "gapbottom 10");
		
		JLabel pwText = new JLabel();
		pwText.setText("Password");
		pwText.setFont(new Font(null,Font.BOLD, 12));
		pwText.setForeground(Color.black);
		panel.add(pwText);
		
		pw = new JPasswordField();
		pw.setText("");
		pw.setColumns(50);
		pw.setFont(new Font(null,Font.PLAIN,15));
		panel.add(pw);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFocusable(false);
		cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancelButton.addActionListener(this);
		//panel.add(cancelButton,"span, split 2, center, gaptop 30");
		
		loginButton = new JButton("Login");
		loginButton.setFocusable(false);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.addActionListener(this);
		panel.add(loginButton, "span, center, gaptop 30");
				
		infolabel = new JLabel();
		infolabel.setText("");
		infolabel.setFont(new Font(null,Font.ITALIC, 15));
		infolabel.setForeground(Color.red);
		infolabel.setVisible(false);
		panel.add(infolabel,"span, center");
		
		ImageIcon image = new ImageIcon("image path");
		
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Simple Authenticator");
		this.setIconImage(image.getImage());
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void resetComponents() {
		emailAddress.setText("");
		pw.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			if(infolabel.isVisible()) infolabel.setVisible(false);			
				
			String email = emailAddress.getText();					
			if(email.isEmpty() && pw.getPassword().length == 0) {
				infolabel.setText("Enter email address and password");
				infolabel.setVisible(true);
			}
			
			else if(! Authenticate.validateEmail(email)) {
				infolabel.setText("Invalid email address");
				infolabel.setVisible(true);
				resetComponents();
			}
			
			else {		
				String password = String.valueOf(pw.getPassword());
				try {
					if(! Authenticate.email(email)) {	
						infolabel.setForeground(Color.red);
						infolabel.setText("User not found");
						infolabel.setVisible(true);
						resetComponents();
						return;
					}
						
					else if(Authenticate.password(email, password)) {
						infolabel.setForeground(Color.green);
						infolabel.setText("Login attempt successful!");
						infolabel.setVisible(true);
						resetComponents();
						return;
					}
					infolabel.setForeground(Color.red);
					infolabel.setText("Login attempt falied!");
					infolabel.setVisible(true);
					resetComponents();		
						
				} 
				
				catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
					e1.printStackTrace();
				}
							
			}
		}
	}
	
	public static void main(String[] args) {
		new MyFrame();  //example@sky.com coventry02
	}
}
