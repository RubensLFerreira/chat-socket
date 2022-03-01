import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
  public static final int PORT = 4000; // declarando a porta
  private ServerSocket serverSocket; // declarando o socket

  public void start() throws IOException {
    serverSocket = new ServerSocket(PORT); // iniciando o servidor
    System.out.println("Servidor iniciado na porta: " + PORT);
    clientConnectionLoop();
  }

  private void clientConnectionLoop() throws IOException {
    while(true){
      Socket clienteSocket = serverSocket.accept(); //(cliente local) vai aguardar um cliente conectar
      System.out.println("Cliente: " + clienteSocket.getRemoteSocketAddress() + " conectou!"); // buscando o cliente pelo endereço
    }
  }
  public static void main(String[] args) {
    try {
      ChatServer server = new ChatServer();
      server.start();
    } catch (IOException e) {
      System.out.println("Erro ao iniciar o servidor " + e.getMessage());
    }
  }
}
