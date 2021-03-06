package Cliente;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class Login extends JFrame {

	private File[] listado;
	//a
	private List list;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try(Socket cliente = new Socket("localhost",6666);
//						DataInputStream dis = new DataInputStream(cliente.getInputStream());
//						DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
//								ObjectInputStream obin = new ObjectInputStream(cliente.getInputStream())) {
//					a frame = new a(cliente,obin,dis,dos);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public Login(Socket s, ObjectInputStream obin, DataInputStream dis, DataOutputStream dos) {
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(null);
		
		

		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Cliente/icono.png")));
		JLabel lblNewLabel = new JLabel("Archivos");
		lblNewLabel.setBounds(10, 11, 198, 14);
		getContentPane().add(lblNewLabel);
		setMinimumSize(new Dimension(500,300));

		list = new List();
		list.setBounds(10, 31, 352, 180);
		getContentPane().add(list);

		JButton btnNewButton = new JButton("Descargar");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int aux = list.getSelectedIndex();
				System.out.println(aux);
				descargar(aux, s, dis, dos);
				String directorio = list.getItem(aux);
				System.out.println(directorio);
				Abrir a = new Abrir(directorio);
				a.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(250, 217, 109, 23);
		getContentPane().add(btnNewButton);

		JButton btnSubir = new JButton("Subir archivos");
		btnSubir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subirArchivo md = new subirArchivo(s,dis,dos);
				md.setVisible(true);
			}
		});
		btnSubir.setBounds(12, 217, 125, 23);
		getContentPane().add(btnSubir);
		
		JButton btnRefresh = new JButton("Refrescar");
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dos.writeUTF("List");
					dos.flush();
					System.out.println("Volviendo a listar");
					listar(obin);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRefresh.setBounds(366, 44, 89, 23);
		getContentPane().add(btnRefresh);

		listar(obin);
	}

	public void descargar(int a, Socket s, DataInputStream dis, DataOutputStream dos) {

		String directorio = list.getItem(a);
		System.out.println("descargando " + directorio + "...");
		try {
			
			dos.writeUTF("GET" + directorio);
			dos.flush();
			
			File f = new File(directorio);
			FileOutputStream fos = new FileOutputStream(f);
			byte[] buf = new byte[1024 * 32];
			int leidos;
			System.out.println(f.getAbsolutePath() + "  " + f.length()+"Mb");
			while ((leidos = dis.read(buf)) != -1) {
				fos.write(buf, 0, leidos);
			}
			if (f.exists()) {
				System.out.println(f.getName() + " descargado");
				System.out.println(f.getAbsolutePath() + "  " + f.length());
			}

			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void listar(ObjectInputStream obin) {
		try {
			listado = (File[]) obin.readObject();
			if (listado.length == 0) {
				list.add("El directorio esta vacio ");
			} else {

				for (File f : listado) {
					list.add(f.getName());
					System.out.println(f.getName());
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
