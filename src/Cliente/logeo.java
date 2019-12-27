package Cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class logeo extends JFrame {

	private JPanel contentPane;
	public JTextField txtUsuario;
	public JPasswordField jpassClave;
	private String clave;
	private String usuario;
	private File[] listado;
	private boolean correcto1;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					logeo frame = new logeo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	public logeo() {
		Base b = new Base();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(40, 35, 54, 16);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(40, 64, 116, 22);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Clave");
		lblNewLabel_1.setBounds(40, 109, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		jpassClave = new JPasswordField();
		jpassClave.setBounds(40, 145, 116, 22);
		contentPane.add(jpassClave);
		
		JButton btnIngresar = new JButton("Iniciar Sesión");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [] Arraylista = null;
				int cont = 0;
				char claveChar[] = jpassClave.getPassword();
				clave = new String(claveChar);
				usuario = txtUsuario.getText();
				boolean correcto = false;
				try
				{
				Socket cliente = new Socket("localhost",6666);
				DataInputStream dis = new DataInputStream(cliente.getInputStream());
				DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
						ObjectInputStream obin = new ObjectInputStream(cliente.getInputStream());
				
					dos.writeUTF(usuario);
					System.out.println(usuario);				
					dos.writeUTF(clave);
					correcto = dis.readBoolean();
					correcto1=correcto;
					if(b.login(usuario, clave) == 1) {
						a md = new a(cliente,obin,dis,dos);
						md.setVisible(true);
					}
				
					if(correcto) {
						a md = new a(cliente,obin,dis,dos);
						md.setVisible(true);
						
						
						
					}else {
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
		btnIngresar.setBounds(40, 195, 116, 25);
		contentPane.add(btnIngresar);
		
		lblNewLabel_2 = new JLabel("Si no tienes usuario, registrate");
		lblNewLabel_2.setBounds(181, 36, 158, 38);
		contentPane.add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(170, 243, 0, -194);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setBounds(215, 85, 89, 23);
		contentPane.add(btnNewButton);
	}
	
	public boolean getCorrecto() {
		return this.correcto1;
	}
	
	
}
