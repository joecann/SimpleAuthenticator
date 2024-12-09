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
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class MyFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton button;
	private JTextField emailAddress;
	private JPasswordField pw;
	private JLabel infolabel;
	
	public MyFrame() {
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(400,400));
		panel.setBackground(new Color(28,28,29));
		panel.setLayout(new MigLayout("center"));
			
		JLabel label = new JLabel();
		label.setText("Password Authenticator");
		label.setFont(new Font(null,Font.BOLD, 30));
		label.setForeground(Color.white);
		panel.add(label,"center,wrap 40px");
		
		emailAddress = new JTextField();
		emailAddress.setColumns(30);
		emailAddress.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(BevelBorder.LOWERED), 
				"Email Address",
				TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP,
				null,
				Color.white));
		emailAddress.setBackground(new Color(28,28,29));
		emailAddress.setFont(new Font(null,Font.BOLD,18));
		emailAddress.setForeground(Color.white);
		panel.add(emailAddress,"center,wrap 15px");
		
		pw = new JPasswordField();
		pw.setText("");
		pw.setColumns(30);
		pw.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(BevelBorder.LOWERED), 
				"Password",
				TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP,
				null,
				Color.white));
		pw.setBackground(new Color(28,28,29));
		pw.setFont(new Font(null,Font.BOLD,18));
		pw.setForeground(Color.white);
		panel.add(pw,"center,wrap 15px");
		
		ImageIcon image = new ImageIcon("imageURL");
		
		button = new JButton(image);
		button.setFocusable(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.addActionListener(this);
		button.setContentAreaFilled(false);
		button.setBorder(null);
		button.setOpaque(true);
		panel.add(button,"center, wrap 20px");
		
		infolabel = new JLabel();
		infolabel.setText("");
		infolabel.setFont(new Font(null,Font.ITALIC, 15));
		infolabel.setForeground(Color.red);
		infolabel.setVisible(false);
		panel.add(infolabel,"center");
		
		
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Simple Authenticator");
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
		if(e.getSource() == button) {
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
						infolabel.setText("Email not found");
						infolabel.setVisible(true);
						resetComponents();
						return;
					}
						
					else if(Authenticate.password(email, password)) {
						infolabel.setForeground(Color.green);
						infolabel.setText("Login attempted successfully!");
						infolabel.setVisible(true);
						resetComponents();
						return;
					}
					infolabel.setForeground(Color.red);
					infolabel.setText("Login attempted falied!");
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
		new MyFrame();
	}
}
