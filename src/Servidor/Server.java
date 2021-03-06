package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	public static void main(String[] args) {

		ExecutorService pool = Executors.newCachedThreadPool();

		try (ServerSocket server = new ServerSocket(6666);) {

			while (true) {
				try {
					Socket cliente = server.accept();
					pool.execute(new AtenderPeticion(cliente));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}

	}

}