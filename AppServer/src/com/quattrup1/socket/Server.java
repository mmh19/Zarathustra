package com.quattrup1.socket;

import com.quattrup1.interfaces.RequestHandler;
import com.example.domenico.Zarathustra.backend.server.Request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private int port;
	private ExecutorService executorService;
	
	public Server(int port) throws IOException {
		this.port = port;
		executorService = Executors.newCachedThreadPool();
	}
	public void listen(RequestHandler requestHandler) throws IOException {
		ServerSocket server = new ServerSocket(port);
		while (true) {
			Socket client = server.accept();
			InputStream input = client.getInputStream();
			OutputStream output = client.getOutputStream();
			executorService.submit(() -> handleRequest(requestHandler, input, output));
		}
	}
	private void handleRequest(RequestHandler requestHandler, InputStream input, OutputStream output) {
		try {
			new ObjectOutputStream(output).writeObject(requestHandler.compute((Request) new ObjectInputStream(input).readObject()));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
