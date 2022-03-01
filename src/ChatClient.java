import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLClientInfoException;
import java.util.Scanner;

public class ChatClient {
  private static  final String SERVER_ADDRESS = "192.168.0.12"; // adicionando o endereço do servidor
  private ClientSocket clienteSocket; // criando o canal lado cliente
  private Scanner scanner;
  //private PrintWriter out; // out é por onde envia os dados

  public ChatClient() {
    scanner = new Scanner(System.in);
  }

  public void start() throws IOException {
    clienteSocket = new ClientSocket(new Socket(SERVER_ADDRESS, ChatServer.PORT)); // interligando com o server
    //clienteSocket.getOutputStream(); // saída de dados (p/ enviar dados)
    System.out.println("Cliente conectado ao servidor em " +  SERVER_ADDRESS + ": " + ChatServer.PORT);
    messageLoop();
  }

  private void messageLoop() throws IOException {
    String msg;
    do{
      System.out.print("Digite uma mensagem (ou 'tchau' para finalizar): ");
      msg = scanner.nextLine(); // receber uma string digitada pelo cliente
      clienteSocket.sendMsg(msg);
    }while(!msg.equalsIgnoreCase("tchau"));
  }

  public static void main(String[] args) {
    try {
      ChatClient cliente = new ChatClient();
      cliente.start();
    } catch (IOException e) {
      System.out.println("Erro ao iniciar cliente: " + e.getMessage());
    }
    System.out.println("Cliente finalizado!");
  }
}