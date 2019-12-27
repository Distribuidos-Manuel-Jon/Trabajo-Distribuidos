package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DescargadorCliente {

	private static Socket soc;
	private static String directorio;

	public DescargadorCliente(Socket soc, String directorio) {

		this.soc = soc;
		this.directorio = directorio;
	}

	public static void main(String[] args) {

		File f = new File(directorio+"descargado");
		byte[] buf = new byte[1024 * 32];
		int leidos;
			System.out.println("Entrando al descargador");
		try (DataInputStream dis = new DataInputStream(soc.getInputStream());
				FileOutputStream fos = new FileOutputStream(f);
				DataOutputStream dos = new DataOutputStream(soc.getOutputStream())) {
			System.out.println(f.getAbsolutePath()+"  "+f.length());
			while ((leidos = dis.read(buf)) != -1) {
				fos.write(buf, 0, leidos);
			}
			if(f.exists()) {
				System.out.println(f.getName()+" descargado");
				System.out.println(f.getAbsolutePath()+"  "+f.length());
			}
			//dos.flush();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
