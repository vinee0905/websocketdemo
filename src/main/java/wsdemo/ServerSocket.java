package wsdemo;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class ServerSocket extends WebSocketServer {

	private ServerManager serverManager;

	public ServerSocket(ServerManager s, int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
		serverManager = s;
	}

	@Override
	public void onClose(WebSocket socket, int message, String reason,
			boolean remote) {
		serverManager.UserLeave(socket);
	}

	@Override
	public void onError(WebSocket socket, Exception message) {
		System.out.println("Socket Exception:" + message.toString());
	}

	@Override
	public void onMessage(WebSocket socket, String message) {
		System.out.println("OnMessage:" + message.toString());

		if (message.equals("1")) {
			serverManager.SendMessageToUser(socket, "What?");
		}

		String[] result = message.split(":");
		if (result.length == 2) {
			if (result[0].equals("user")) {
				serverManager.UserLogin(result[1], socket);
			}
		}
	}

	@Override
	public void onOpen(WebSocket socket, ClientHandshake handshake) {
		System.out.println("Some one Connected...");
	}

}