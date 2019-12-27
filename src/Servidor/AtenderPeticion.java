package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AtenderPeticion implements Runnable{

	Socket cliente;

	public AtenderPeticion(Socket c) {
		super();
		this.cliente = c;
	}
	
	public void run() {
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

		try (DataInputStream dis = new DataInputStream(cliente.getInputStream());
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	}

	//a


