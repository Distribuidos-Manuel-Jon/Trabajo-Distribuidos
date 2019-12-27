package Cliente;

import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class p extends JFrame{
	
	private File[] listado;
	
	private List list;
	private JTextField textField_1;
	private JPasswordField passwordField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try(Socket cliente = new Socket("localhost",6666);
						DataInputStream dis = new DataInputStream(cliente.getInputStream());
						DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
								ObjectInputStream obin = new ObjectInputStream(cliente.getInputStream())) {
					p frame = new p(obin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public p(ObjectInputStream obin) {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(23, 35, 49, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setBounds(23, 91, 62, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(99, 32, 96, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(99, 88, 96, 20);
		getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setBounds(106, 151, 89, 23);
		getContentPane().add(btnNewButton);
		
		
	}
}
