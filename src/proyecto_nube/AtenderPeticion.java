package proyecto_nube;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class AtenderPeticion implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
	
	/*public AtenderPeticion(Socket cliente) {
		super();
		this.cliente = cliente;
	}



	public void run() {
		
		Socket cliente;
		String us = "usuario1";
		String pa = "1234";
		
		SSLServerSocket s;
		SSLServerSocketFactory sslSrvFact =	(SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		try {
			s =(SSLServerSocket)sslSrvFact.createServerSocket(443);
			SSLSocket c = (SSLSocket)s.accept();
			DataOutputStream out = new DataOutputStream( c.getOutputStream());
			DataInputStream in = new DataInputStream(c.getInputStream());
			String uss = in.readUTF();
			System.out.println("usuario recibido: "+uss);
			String pas = in.readLine();
			if(uss.equals(us) && pas.equals(pa)) {
				out.writeBoolean(true);
			}else {
				out.writeBoolean(false);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}*/

		
}
