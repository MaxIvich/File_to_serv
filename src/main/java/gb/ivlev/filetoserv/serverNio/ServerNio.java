package gb.ivlev.filetoserv.serverNio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class ServerNio {


    public static void main(String[] args) {
        int port = 8899;
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Сервер стартовал на порту. "+ port +" Ожидаем соединения....");
            while (true){
                selector.select();
                for(SelectionKey event : selector.selectedKeys()){
                    if(event.isValid()){
                        try {
                            if(event.isAcceptable()){
                                SocketChannel socketChannel = serverSocketChannel.accept();
                                socketChannel.configureBlocking(false);
                                System.out.println("Пожключен  " + socketChannel.getRemoteAddress());
                                socketChannel.register(selector,SelectionKey.OP_READ);
                            } else if (event.isReadable()) {
                                SocketChannel socketChannel = (SocketChannel) event.channel();
                                handleReadable(socketChannel);
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleReadable(SocketChannel socketChannel) {

    }

}
