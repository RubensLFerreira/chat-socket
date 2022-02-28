import java.io.IOException;
import java.net.Socket;

public class ChatClient {
  private static  final String SERVER_ADDRESS = "127.0.0.1"; // adicionando o endereço do servidor
  private Socket clienteSocket; // criando o canal lado cliente

  public void start() throws IOException {
    clienteSocket = new Socket("127.0.0.1", ChatServer.PORT); // interligando com o server
  }
  public static void main(String[] args) {
    try {
      ChatClient cliente = new ChatClient();
      cliente.start();
    } catch (IOException e) {
      //System.out.println("Erro ao iniciar cliente: " + e.getMessage());
      e.printStackTrace();

    }
  }
}
