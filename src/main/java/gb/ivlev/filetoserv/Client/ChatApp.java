package gb.ivlev.filetoserv.Client;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class ChatApp extends Application {

    String title ="Файлообменник";
    String Url = "/gb/ivlev/filetoserv/hello-view.fxml";


    @Override
    public void start(Stage  primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource(Url);
        Parent parent = FXMLLoader.load(fxmlLocation);
        //Parent parent = FXMLLoader.load(Objects.requireNonNull(ChatApp.class.getResource("hello-view.fxml")));
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle(title);
        primaryStage.show();



    }

}
