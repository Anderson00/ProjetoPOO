import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	
	public static void main(String args[]){
		ServerSocket server = null;
		try {
			System.out.println("Server iniciando...");
			server = new ServerSocket(32444);
			System.out.println("Pronto");
			
			while(true){
				Socket client = server.accept();
				Thread t = new Thread(new ClientThread(client));
				t.start();				
			}			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
}
