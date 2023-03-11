package gb.ivlev.filetoserv.Client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
//  fx:controller="gb.ivlev.filetoserv.Client.ChatController"

public class ChatController implements Initializable {
    public ListView <String>listview;
    public TextField input;

    private IoNet net;


    public void sendMsg(ActionEvent actionEvent) throws IOException {
        net.sendMsg(input.getText());
        input.clear();
    }
    public void quitMsg(ActionEvent actionEvent) throws IOException {
        net.sendMsg("quit");


    }

    private void addMessage(String msg){
        Platform.runLater(()-> listview.getItems().add(msg));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Socket socket = new Socket("localhost",8189);
            net = new IoNet(this::addMessage,socket);
        } catch(Exception e){
            e.printStackTrace();
        }


    }
}
