module gb.ivlev.filetoserv {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens gb.ivlev.filetoserv to javafx.fxml;
    exports gb.ivlev.filetoserv.Client;
    exports gb.ivlev.filetoserv.server;


//C:\Users\jylve\IdeaProjects\File-to-serv\src\main\resources\gb\ivlev\filetoserv\hello-view.fxml
}