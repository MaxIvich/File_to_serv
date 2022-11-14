package gb.ivlev.filetoserv.Client;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class IoNet implements Closeable {
    private Callback callback;
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    private byte[] buf;

    public IoNet(Callback callback, Socket socket) throws IOException {
        this.callback = callback;
        this.socket = socket;
        is = socket.getInputStream();
        os = socket.getOutputStream();
        buf = new byte[8192];
        Thread readTread = new Thread(this::readMessages);
        readTread.setDaemon(true);
        readTread.start();

    }
    public void sendMsg(String msg) throws IOException {
        os.write(msg.getBytes(StandardCharsets.UTF_8));
        os.flush();
    }


    void readMessages()  {
        try{ while (true) {
            int read = is.read(buf);

            String msg = new String(buf, 0, read).trim();
            callback.onReceive(msg);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
     @Override
    public void close(){
        try{is.close();
            os.close();
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
