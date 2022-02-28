import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {
  public static final int PORT = 4000; // declarando a porta
  private ServerSocket serverSocket; // declarando o socket

  public void start() throws IOException {
    serverSocket = new ServerSocket(4000); // iniciando o servidor
  }

  public static void main(String[] args) {
    try {
      ChatServer server = new ChatServer();
      server.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
