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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Registrar extends JFrame{
	
	
	private JTextField txtUsuario;
	private JPasswordField jpassClave;
	private String clave;
	private String usuario;
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try(Socket cliente = new Socket("localhost",6666);
//						DataInputStream dis = new DataInputStream(cliente.getInputStream());
//						DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
//								ObjectInputStream obin = new ObjectInputStream(cliente.getInputStream())) {
//					p frame = new p();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public Registrar() {
		MetodoRegistrar r = new MetodoRegistrar();
		getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(23, 35, 49, 14);
		getContentPane().add(lblUsuario);
		
		JLabel lblclave = new JLabel("Clave");
		lblclave.setBounds(23, 91, 62, 14);
		getContentPane().add(lblclave);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(99, 32, 96, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		jpassClave = new JPasswordField();
		jpassClave.setBounds(99, 88, 96, 20);
		getContentPane().add(jpassClave);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char claveChar[] = jpassClave.getPassword();
				clave = new String(claveChar);
				usuario = txtUsuario.getText();
				try(Socket cliente = new Socket("localhost",6666);
						DataInputStream dis = new DataInputStream(cliente.getInputStream());
						DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
								ObjectInputStream obin = new ObjectInputStream(cliente.getInputStream());)
				{
				
					if(r.registrar(usuario, clave) == 1) {
						File f = new File(usuario);
						f.mkdir();
					}  else {
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
					}
					
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnRegistrar.setBounds(106, 151, 89, 23);
		getContentPane().add(btnRegistrar);
		
		
	}
	//a
}
