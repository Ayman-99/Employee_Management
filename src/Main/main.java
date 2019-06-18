package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class main extends Application {
    private static Stage rootStage;
    public static Scene scene;
    public static AnchorPane rootPane;
    
    public main() throws IOException{

    }
    public static Stage getStage(){
        return rootStage;
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        rootStage = primaryStage;
        primaryStage.setResizable(false);
        rootPane = FXMLLoader.load(getClass().getResource("/LoginStuff/Login.fxml"));
        scene = new Scene(rootPane);
        primaryStage.setTitle("Employee");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
