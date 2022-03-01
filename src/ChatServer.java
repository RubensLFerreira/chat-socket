import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
      ClientSocket clienteSocket = new ClientSocket(serverSocket.accept()); //(cliente local) vai aguardar um cliente conectar
      new Thread(() -> clientMessageLoop(clienteSocket)).start();
    }
  }
  public void clientMessageLoop(ClientSocket clientSocket) { // receber msgs em loop
    String msg;
    try {
      while((msg = clientSocket.getMessage()) != null) {
        if ("tchau".equalsIgnoreCase(msg))
          return;

        System.out.printf("Mensagem recebida do cliente %s: %s\n",
                clientSocket.getRemoteSocketAddress(),
                msg);
      }
    } finally {
      clientSocket.close();
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