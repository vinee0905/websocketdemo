package wsdemo;

public class TestServer {

	public static void main(String[] args) {
		ServerManager serverManager = new ServerManager();
		serverManager.Start(8888);
	}

}
