import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ChatServer {
  public static final int PORT = 4000; // declarando a porta
  private ServerSocket serverSocket; // declarando o socket
  private final List<ClientSocket> clients = new LinkedList<>();

  public void start() throws IOException {
    serverSocket = new ServerSocket(PORT); // iniciando o servidor
    System.out.println("Servidor iniciado na porta: " + PORT);
    clientConnectionLoop();
  }

  private void clientConnectionLoop() throws IOException {
    while(true){
      ClientSocket clienteSocket = new ClientSocket(serverSocket.accept()); //(cliente local) vai aguardar um cliente conectar
      clients.add(clienteSocket);
      new Thread(() -> clientMessageLoop(clienteSocket)).start();
    }
  }
  private void clientMessageLoop(ClientSocket clientSocket) { // receber msgs em loop
    String msg;
    try {
      while((msg = clientSocket.getMessage()) != null) {
        if ("tchau".equalsIgnoreCase(msg))
          return;

        System.out.printf("Mensagem recebida do cliente %s: %s\n",
                clientSocket.getRemoteSocketAddress(),
                msg);
        sendMsgToAll(clientSocket, msg);
      }
    } finally {
      clientSocket.close();
    }
  }

  private void sendMsgToAll(ClientSocket sender, String msg) {
    Iterator<ClientSocket> iterator = clients.iterator();
    while (iterator.hasNext()) {
      ClientSocket clientSocket = iterator.next();
      if (!sender.equals(clientSocket)) {
        if (!clientSocket.sendMsg("cliente " + sender.getRemoteSocketAddress() + ": " + msg)) {
          iterator.remove();
        }
      }
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