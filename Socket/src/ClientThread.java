import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;


public class ClientThread implements Runnable {
	
	private Socket client;
	private StringBuffer msg;
	
	public ClientThread(Socket client) {
		this.client = client;
		//msg = new StringBuffer();
	}

	@Override
	public void run() {
		ObjectInputStream clientInput = null;
		ObjectOutputStream clientOutput = null;
		try{	
			clientOutput = new ObjectOutputStream(client.getOutputStream());
			clientOutput.flush();
			clientInput = new ObjectInputStream(client.getInputStream());
			
			
			
			try {
				String msg = (String) clientInput.readObject();
				System.out.println(">> "+msg.toString());
				String[] info = msg.toString().split(";");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(info[0].equals("Alucard") && info[1].equals("qwe123")){
					clientOutput.writeBoolean(true);
					clientOutput.flush();
				}else{
					clientOutput.writeBoolean(false);
					clientOutput.flush();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			try{
				clientInput.close();
				clientOutput.close();
				client.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
