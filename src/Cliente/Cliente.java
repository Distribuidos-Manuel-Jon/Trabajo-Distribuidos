package Cliente;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;

public class Cliente {
	
	
	public static void main(String[] args) {

//		File[] listado;
//		File fich = new File("C:\\Users\\maroh\\Downloads");
//		long tam;
//		byte[] b = new byte[1024];
		//credenciales();
		
//		String usuario ="usuario1";
//		String clave = "1234";
//		boolean correcto;
////		try(Socket cliente = new Socket("localhost",6666);
//				DataInputStream dis = new DataInputStream(cliente.getInputStream());
//				DataOutputStream dos = new DataOutputStream(cliente.getOutputStream())){
//			dos.writeBytes(usuario);	
//			dos.flush();
//			dos.writeBytes(clave);
//			dos.flush();
//			correcto = dis.readBoolean();
//			
//			if(correcto) {
//				System.out.println("Bienvenido");
//				JOptionPane.showMessageDialog(null, "Bienvenido");
//				
//			}else {
//				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
//			}
//			
//		} catch (UnknownHostException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
	
		
		
		logeo log = new logeo();
		log.setVisible(true);
//		correcto=log.getCorrecto();
//		System.out.println(correcto);
		
		
		
		
//		listado = fich.listFiles();
//		mostrarFicheros(listado);
		
//		try(Socket cliente = new Socket("localhost", 7777);
//				DataInputStream dis = new DataInputStream(cliente.getInputStream());
//				DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
//				BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));){
//			
//			dos.writeBytes("LISTAR");
//			tam = dis.readLong();
//			//int tamI =(int)tam;
//			int leido = dis.read(b);
//			while(leido != -1) {
//			leido = dis.read(b);
//			}
//			
//			
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		/*try(Socket cliente = new Socket("localhost", 6666);){
				credenciales();
			
		}
		
		String path = "C:/Users/maroh/Downloads/test.pdf";
		
		//abrirArchivo(path);
		
		try (Stream<Path> walk = Files.walk(Paths.get("C:/Users/maroh/Downloads"))) {

			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());

			result.forEach(System.out::println);

		} */
	}
	
	
	
	
	

}
