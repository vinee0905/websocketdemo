package wsdemo;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;

import org.java_websocket.WebSocket.READYSTATE;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

public class TestClient {
	public static WebSocketClient client;

	public static void main(String[] args) throws URISyntaxException, NotYetConnectedException, UnsupportedEncodingException {
	    client = new WebSocketClient(new URI("ws://localhost:8888"),new Draft_17()) {

	        @Override
	        public void onOpen(ServerHandshake arg0) {
	            System.out.println("������");
	        }

	        @Override
	        public void onMessage(String arg0) {
	            System.out.println("�յ���Ϣ"+arg0);
	        }

	        @Override
	        public void onError(Exception arg0) {
	            arg0.printStackTrace();
	            System.out.println("���������ѹر�");
	        }

	        @Override
	        public void onClose(int arg0, String arg1, boolean arg2) {
	            System.out.println("�����ѹر�");
	        }

	        @Override
	        public void onMessage(ByteBuffer bytes) {
	            try {
	                System.out.println(new String(bytes.array(),"utf-8"));
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            }
	        }


	    };

	    client.connect();

	    while(!client.getReadyState().equals(READYSTATE.OPEN)){
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        System.out.println("��û�д�");
	    }
	    System.out.println("����");
	    client.send("hello world");
//	    client.send("hello world2".getBytes("utf-8"));
	}

}
