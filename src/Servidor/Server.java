package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Server {

	public static void main(String[] args) {

		String us = "usuario1";
		String pa = "1234";
		String usR = null;
		String paR = null;
		String comando = null;
		String dir;
		byte[] buff = new byte[1024 * 32];
		boolean correcto = false;
		File fich;
		File[] listado;


		try (ServerSocket server = new ServerSocket(6666);) {
			while (true) {
				try (Socket cliente = server.accept();
						DataInputStream dis = new DataInputStream(cliente.getInputStream());
						DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
						ObjectOutputStream obout = new ObjectOutputStream(cliente.getOutputStream())) {

					System.out.println("Entrando al servidor");
					usR = dis.readUTF();
					System.out.println("Usuario recivido " + usR);
					paR = dis.readUTF();
					if (usR.equals(us) && paR.equals(paR)) {
						correcto = true;
					}

					dos.writeBoolean(correcto);
					dos.flush();

					fich = new File(usR);
					if (!fich.exists()) {
						fich.mkdir();
					}
					listado = fich.listFiles();
					obout.writeObject(listado);
					obout.flush();
					System.out.println("Objeto enviado, espearando orden");
					comando = dis.readUTF();

					// while(!comando.equals("END")) {
					if (comando.startsWith("GET")) {

						dir = comando.substring(3);
						System.out.println("Enviando " + dir);
						File f = new File(usR + "\\" + dir);
						System.out.println(f.getAbsolutePath() + " " + f.length() + f.getName());
						try (FileInputStream fis = new FileInputStream(f)) {
							// System.out.println(usR+"\\"+dir);
							int leidos;
							while ((leidos = fis.read(buff)) != -1) {
								dos.write(buff, 0, leidos);
							}
							dos.flush();
						}
					}
					if (comando.startsWith("PUT")) {
						dir = comando.substring(3);
						System.out.println("Subiendo " + dir);
						int aux2 = comando.lastIndexOf("\\");
						String aux = comando.substring(aux2);
						System.out.println(aux);
						File f = new File(dir);
						System.out.println(f.getAbsolutePath());
							
							// System.out.println(usR+"\\"+dir);
							try (FileInputStream fis = new FileInputStream(f);
									FileOutputStream fos = new FileOutputStream(usR + "\\" + aux)) {
								int leidos;
								while ((leidos = fis.read(buff)) != -1) {
									fos.write(buff, 0, leidos);
									System.out.println("aa");
								}
							}
							
					}
				}

			}
			// }

		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * ExecutorService pool = Executors.newCachedThreadPool();
		 * 
		 * try (ServerSocket server = new ServerSocket(6666);){
		 * 
		 * while(true) { try{ Socket cliente = server.accept(); //aqui no se puede
		 * utilizar el try with resources pool.execute(new AtenderPeticion(cliente)); }
		 * catch (IOException e) { e.printStackTrace(); }
		 * 
		 * } } catch (IOException e) { e.printStackTrace(); }finally { pool.shutdown();
		 * }
		 */
	}


}
