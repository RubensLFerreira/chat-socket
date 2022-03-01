import java.io.IOException;
import java.net.Socket;

public class ChatClient {
  private static  final String SERVER_ADDRESS = "192.168.0.12"; // adicionando o endereço do servidor
  private Socket clienteSocket; // criando o canal lado cliente

  public void start() throws IOException {
    clienteSocket = new Socket(SERVER_ADDRESS, ChatServer.PORT); // interligando com o server
    System.out.println("Cliente conectado ao servidor em " +  SERVER_ADDRESS + ": " + ChatServer.PORT);
  }
  public static void main(String[] args) {
    try {
      ChatClient cliente = new ChatClient();
      cliente.start();
    } catch (IOException e) {
      System.out.println("Erro ao iniciar cliente: " + e.getMessage());
      e.printStackTrace();

    }
    System.out.println("Cliente finalizado!");
  }
}