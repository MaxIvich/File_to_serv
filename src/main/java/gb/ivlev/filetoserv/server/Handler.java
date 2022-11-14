package gb.ivlev.filetoserv.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Handler  implements Runnable{


    private boolean running;
    private byte[] buf;
    private DataInputStream is;
    private DataOutputStream os;

    private Socket socket;

    public Handler(Socket socket) throws IOException {
        running = true;
        buf = new byte[8192];
        this.socket = socket;

        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());

    }

    public void close() throws IOException {
        is.close();
        os.close();
        socket.close();
    }

    public void run() {
        try {
            while(running){
                int read = is.read(buf);
                String message = new String(buf, 0,read)
                        .trim();
                if(message.equals("quit")){
                    os.write("Client disconnected \n".getBytes(StandardCharsets.UTF_8));
                    close();
                }
                System.out.println("Received : " + message);
                os.write((message + "\n").getBytes(StandardCharsets.UTF_8));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
